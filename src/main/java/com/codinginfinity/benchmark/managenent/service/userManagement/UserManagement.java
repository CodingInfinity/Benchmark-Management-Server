package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.*;

/**
 * Created by andrew on 2016/06/19.
 */
public interface UserManagement {

    ActivateRegistrationResponse activateRegistration(ActivateRegistrationRequest request) throws NotAuthorizedException;
    RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request) throws NotAuthorizedException, UserNotActivatedException;
    CompletePasswordResetResponse completePasswordReset(CompletePasswordResetRequest request) throws NotAuthorizedException;
    CreateUnmanagedUserResponse createUnmanagedUser(CreateUnmanagedUserRequest request) throws DuplicateUsernameException;
    CreateManagedUserResponse createManagedUser(CreateManagedUserRequest request) throws DuplicateUsernameException;
    UpdateUserResponse updateUser(UpdateUserRequest request) throws NotAuthorizedException, NonExistentException;
    DeleteUserResponse deleteUser(DeleteUserRequest request) throws NonExistentException;
    ChangePasswordResponse changePassword(ChangePasswordRequest request) throws NonExistentException;
    GetUserWithAuthoritiesByLoginResponse getUserWithAuthoritiesByLogin(GetUserWithAuthoritiesByLoginRequest request) throws NonExistentException;
    GetUserWithAuthoritiesByIdResponse getUserWithAuthoritiesById(GetUserWithAuthoritiesByIdRequest request) throws NonExistentException;
    GetUserWithAuthoritiesResponse getUserWithAuthorities(GetUserWithAuthoritiesRequest request) throws NonExistentException;
}
