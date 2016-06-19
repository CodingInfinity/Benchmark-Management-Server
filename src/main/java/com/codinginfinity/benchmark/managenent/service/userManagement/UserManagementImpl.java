package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.RequestPasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.RequestPasswordResetResponse;
import com.codinginfinity.benchmark.managenent.service.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
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
}
