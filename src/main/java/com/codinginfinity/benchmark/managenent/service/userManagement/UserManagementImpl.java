package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
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
    public CreateUserResponse createUser(CreateUserRequest request) throws DuplicateUsernameException {
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
        log.debug("Created Information for User: {}", newUser);
        return new CreateUserResponse(newUser);
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
}
