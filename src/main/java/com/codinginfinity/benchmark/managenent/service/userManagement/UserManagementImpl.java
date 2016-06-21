package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.security.SecurityUtils;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.*;
import com.codinginfinity.benchmark.managenent.service.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created by andrew on 2016/06/19.
 */
@Service
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

        user.get().setActivated(true);
        user.get().setActivationKey(null);
        User savedUser = userRepository.save(user.get());
        notification.sendActivationEmail(new SendActivationEmailRequest(savedUser));
        log.debug("Activated user: {}", savedUser);
        return new ActivateRegistrationResponse(savedUser);
    }

    @Override
    public RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request)
            throws NotAuthorizedException, UserNotActivatedException {

        Optional<User> user = userRepository.findOneByEmail(request.getEmail());
        if (!user.isPresent()) {
            throw new NotAuthorizedException("Invalid email address");
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

        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.get().setResetDate(null);
        user.get().setResetKey(null);
        return new CompletePasswordResetResponse(userRepository.save(user.get()));
    }

    @Override
    public CreateUnmanagedUserResponse createUnmanagedUser(CreateUnmanagedUserRequest request) throws DuplicateUsernameException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setActivated(false);
        Set<Authority> authorities = new HashSet<>();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        newUser = userRepository.save(newUser);
        notification.sendCreationEmail(new SendCreationEmailRequest(newUser));
        log.debug("Created Information for User: {}", newUser);

        return new CreateUnmanagedUserResponse(newUser);
    }

    @Override
    public CreateManagedUserResponse createManagedUser(CreateManagedUserRequest request) throws DuplicateUsernameException {
        Optional<User> user = userRepository.findOneByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        String encryptedPassword = passwordEncoder.encode(RandomUtils.generatePassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setActivated(true);
        if (request.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            request.getAuthorities().stream().forEach(
                    authority -> authorities.add(authorityRepository.findOne(authority))
            );
            newUser.setAuthorities(authorities);
        }
        newUser.setResetKey(RandomUtils.generateResetKey());
        newUser.setResetDate(ZonedDateTime.now());
        newUser = userRepository.save(newUser);
        notification.sendActivationEmail(new SendActivationEmailRequest(newUser));
        log.debug("Created Information for User: {}", newUser);
        return new CreateManagedUserResponse(newUser);
    }

    @Override
    //ToDo: Find a way to mock static methods under Mockito to be able to write unit tests for this method.
    public UpdateUserResponse updateUser(UpdateUserRequest request) throws NotAuthorizedException, NonExistentException {
        Optional<User> user = userRepository.findOneByUsername(SecurityUtils.getCurrentUsername());
        if (!user.isPresent()) {
            throw new NonExistentException("User in current security context doesn't exist");
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
        Optional<User> user = userRepository.findOneByUsername(request.getUsername());
        if (!user.isPresent()) {
            throw new NonExistentException("User does not exist");
        }

        user.get().getAuthorities().size();
        return new GetUserWithAuthoritiesByLoginResponse(user.get());
    }

    @Override
    public GetUserWithAuthoritiesByIdResponse getUserWithAuthoritiesById(GetUserWithAuthoritiesByIdRequest request) throws NonExistentException {
        Optional<User> user = userRepository.findOneById(request.getId());
        if (!user.isPresent()) {
            throw new NonExistentException("User does not exist");
        }

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
