package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.RequestPasswordResetRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.RequestPasswordResetResponse;

/**
 * Created by andrew on 2016/06/19.
 */
public interface UserManagement {

    RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request) throws NotAuthorizedException, UserNotActivatedException;
}
