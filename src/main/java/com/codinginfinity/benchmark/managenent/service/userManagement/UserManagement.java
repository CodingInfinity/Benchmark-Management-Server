package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.ActivateRegistrationRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CompletePasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateUserRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.RequestPasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.ActivateRegistrationResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.CompletePasswordResetResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.CreateUserResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.RequestPasswordResetResponse;

/**
 * Created by andrew on 2016/06/19.
 */
public interface UserManagement {

    ActivateRegistrationResponse activateRegistration(ActivateRegistrationRequest request) throws NotAuthorizedException;
    RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request) throws NotAuthorizedException, UserNotActivatedException;
    CompletePasswordResetResponse completePasswordReset(CompletePasswordResetRequest request) throws NotAuthorizedException;
    CreateUserResponse createUser(CreateUserRequest request) throws DuplicateUsernameException;
}
