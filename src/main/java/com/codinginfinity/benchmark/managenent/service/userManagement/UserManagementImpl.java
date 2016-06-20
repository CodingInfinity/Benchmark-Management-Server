package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.ActivateRegistrationRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CompletePasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.RequestPasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.ActivateRegistrationResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.CompletePasswordResetResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.RequestPasswordResetResponse;
import com.codinginfinity.benchmark.managenent.service.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/19.
 */
@Service
@Slf4j
public class UserManagementImpl implements UserManagement {

    @Inject
    private UserRepository userRepository;

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
}
