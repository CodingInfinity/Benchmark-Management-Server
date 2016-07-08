package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.security.SecurityUtils;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.*;
import com.codinginfinity.benchmark.managenent.service.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * A reference implementation of the {@link UserManagement} service contract.
 *
 * @see com.codinginfinity.benchmark.managenent.service.notification.exception
 * @see com.codinginfinity.benchmark.managenent.service.notification.request
 * @see com.codinginfinity.benchmark.managenent.service.notification.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */


@Service
@Transactional
@Slf4j
public class UserManagementImpl implements UserManagement {

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private Notification notification;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public ActivateRegistrationResponse activateRegistration(ActivateRegistrationRequest request) throws NotAuthorizedException {
        log.debug("Activating user for activation key {}", request.getKey());

        Optional<User> user = userRepository.findOneByActivationKey(request.getKey());
        if (!user.isPresent()) {
            throw new NotAuthorizedException("Invalid activation key");
        }
        /* Set user to activated and remove activation key, as this is a once off process */
        user.get().setActivated(true);
        user.get().setActivationKey(null);
        User savedUser = userRepository.save(user.get());
        log.debug("Activated user: {}", savedUser);
        return new ActivateRegistrationResponse(savedUser);
    }

    @Override
    public RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request)
            throws EmailNotRegisteredException, NotAuthorizedException, UserNotActivatedException, EmailNotSentException {

        Optional<User> user = userRepository.findOneByEmail(request.getEmail());
        if (!user.isPresent()) {
            throw new EmailNotRegisteredException("Invalid email address");
        }

        if (!user.get().isActivated()) {
            throw new UserNotActivatedException("User is not activated");
        }

        if (Objects.nonNull(user.get().getResetKey())) {
            throw new NotAuthorizedException("User has outstanding reset requests");
        }

        user.get().setResetKey(RandomUtils.generateResetKey());
        user.get().setResetDate(ZonedDateTime.now());
        user = Optional.of(userRepository.save(user.get()));
        notification.sendPasswordResetMail(new SendPasswordResetMailRequest(user.get()));
        return new RequestPasswordResetResponse(user);
    }

    @Override
    public CompletePasswordResetResponse completePasswordReset(CompletePasswordResetRequest request) throws NotAuthorizedException {
        log.debug("Reset user password for reset key {}", request.getKey());

        Optional<User> user = userRepository.findOneByResetKey(request.getKey());
        if (!user.isPresent()) {
            throw new NotAuthorizedException("Invalid reset key");
        }

        ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
        if (user.get().getResetDate().isBefore(oneDayAgo)) {
            throw new NotAuthorizedException("Reset key expired");
        }

        /* After successful reset, clear the reset date and key as to allow for future recovery of account */
        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.get().setResetDate(null);
        user.get().setResetKey(null);
        return new CompletePasswordResetResponse(userRepository.save(user.get()));
    }

    @Override
    public CreateUnmanagedUserResponse createUnmanagedUser(CreateUnmanagedUserRequest request) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername().toLowerCase());
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Username already exists");
        }

        user = userRepository.findOneByEmail(request.getEmail());
        if(user.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername().toLowerCase());
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setActivated(false);
        /*
         * As this is a user registering themselves, we don't want to allow the user to assign themselves the
         * role of administrator. Default to role of USER.
         */
        Set<Authority> authorities = new HashSet<>();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        /*
         * User will need to activate there account, hence generate an activation key and send email with link
         * to user.
         */
        newUser.setActivationKey(RandomUtils.generateActivationKey());
        newUser = userRepository.save(newUser);
        notification.sendCreationEmail(new SendCreationEmailRequest(newUser));
        log.debug("Created Information for User: {}", newUser);

        return new CreateUnmanagedUserResponse(newUser);
    }

    @Override
    public CreateManagedUserResponse createManagedUser(CreateManagedUserRequest request) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername().toLowerCase());
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Username already exists");
        }

        user = userRepository.findOneByEmail(request.getEmail());
        if(user.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername().toLowerCase());
        /*
         * We assign user a random password as we will send the user an email with to allow them the opportunity to
         * select their own password,
         */
        String encryptedPassword = passwordEncoder.encode(RandomUtils.generatePassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setActivated(true);
        /*
         * As this user is created by an administrator, we want to allow the admin the opportunity to specify a user
         * role, if the admin specifies none, default to role of USER.
         */
        Set<Authority> authorities = new HashSet<>();
        if (request.getAuthorities() != null && request.getAuthorities().size() > 0) {
            request.getAuthorities().stream().forEach(
                    authority -> authorities.add(authorityRepository.findOne(authority))
            );
            newUser.setAuthorities(authorities);
        } else {
            authorities.add(authorityRepository.findOne(AuthoritiesConstants.USER));
        }
        /*
         * To provide the user with the opportunity to select their own password, is the exact same flow as though
         * an already registered user has lost their password to their account. Hence we set the reset date and reset
         * key on the account in question.
         */
        newUser.setResetKey(RandomUtils.generateResetKey());
        newUser.setResetDate(ZonedDateTime.now());
        newUser = userRepository.save(newUser);
        notification.sendActivationEmail(new SendActivationEmailRequest(newUser));
        log.debug("Created Information for User: {}", newUser);
        return new CreateManagedUserResponse(newUser);
    }

    @Override
    //ToDo: Find a way to mock static methods under Mockito to be able to write unit tests for this method.
    public UpdateUserResponse updateUser(UpdateUserRequest request) throws NonExistentException, EmailAlreadyExistsException {
        Optional<User> user = userRepository.findOneByUsername(SecurityUtils.getCurrentUsername());
        if (!user.isPresent()) {
            throw new NonExistentException("User in current security context doesn't exist");
        }

        Optional<User> userWithEmail = userRepository.findOneByEmail(request.getEmail());
        if (userWithEmail.isPresent() && !userWithEmail.get().equals(user.get())){
            throw new EmailAlreadyExistsException();
        }

        user.get().setFirstName(request.getFirstName());
        user.get().setLastName(request.getLastName());
        user.get().setEmail(request.getEmail());
        User savedUser = userRepository.save(user.get());
        log.debug("Changed Information for User: {}", user.get());
        return new UpdateUserResponse(savedUser);
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request) throws NonExistentException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername());
        if (!user.isPresent()) {
            throw new NonExistentException("User does not exist");
        }
        userRepository.delete(user.get());
        log.debug("Deleted User: {}", user.get());
        return new DeleteUserResponse(user.get());
    }

    @Override
    //ToDo: Find a way to mock static methods under Mockito to be able to write unit tests for this method.
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) throws NonExistentException {
        Optional<User> user = userRepository.findOneByUsername(SecurityUtils.getCurrentUsername());
        if (!user.isPresent()) {
            throw new NonExistentException("User in current security context doesn't exist");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        user.get().setPassword(encryptedPassword);
        userRepository.save(user.get());
        log.debug("Changed password for User: {}", user.get());
        return new ChangePasswordResponse();
    }

    @Override
    public GetUserWithAuthoritiesByLoginResponse getUserWithAuthoritiesByLogin(GetUserWithAuthoritiesByLoginRequest request) throws NonExistentException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername().toLowerCase());
        if (!user.isPresent()) {
            throw new NonExistentException("User does not exist");
        }

        /*
         * As this is a many-to-many relationship on the ORM, the mapper will default to lazy initialization. Issue
         * a request on the object to force ORM to retrieve rest of object graph.
         */
        user.get().getAuthorities().size();
        return new GetUserWithAuthoritiesByLoginResponse(user.get());
    }

    @Override
    public GetUserWithAuthoritiesByIdResponse getUserWithAuthoritiesById(GetUserWithAuthoritiesByIdRequest request) throws NonExistentException {
        Optional<User> user = userRepository.findOneById(request.getId());
        if (!user.isPresent()) {
            throw new NonExistentException("User does not exist");
        }

        /*
         * As this is a many-to-many relationship on the ORM, the mapper will default to lazy initialization. Issue
         * a request on the object to force ORM to retrieve rest of object graph.
         */
        user.get().getAuthorities().size();
        return new GetUserWithAuthoritiesByIdResponse(user.get());
    }

    @Override
    //ToDo: Find a way to mock static methods under Mockito to be able to write unit tests for this method.
    public GetUserWithAuthoritiesResponse getUserWithAuthorities(GetUserWithAuthoritiesRequest request) throws NonExistentException {
        User user = getUserWithAuthoritiesByLogin(new GetUserWithAuthoritiesByLoginRequest(SecurityUtils.getCurrentUsername())).getUser();
        return new GetUserWithAuthoritiesResponse(user);
    }
}
