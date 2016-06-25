package com.codinginfinity.benchmark.managenent.service.userManagement;

import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.notification.exception.EMailNotSentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.*;

/**
 * Created by andrew on 2016/06/19.
 */
public interface UserManagement {

    ActivateRegistrationResponse activateRegistration(ActivateRegistrationRequest request) throws NotAuthorizedException;
    RequestPasswordResetResponse requestPasswordReset(RequestPasswordResetRequest request) throws EmailNotRegisteredException, NotAuthorizedException, UserNotActivatedException, EMailNotSentException;
    CompletePasswordResetResponse completePasswordReset(CompletePasswordResetRequest request) throws NotAuthorizedException;
    CreateUnmanagedUserResponse createUnmanagedUser(CreateUnmanagedUserRequest request) throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException;
    CreateManagedUserResponse createManagedUser(CreateManagedUserRequest request) throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException;
    UpdateUserResponse updateUser(UpdateUserRequest request) throws NotAuthorizedException, NonExistentException;
    DeleteUserResponse deleteUser(DeleteUserRequest request) throws NonExistentException;
    ChangePasswordResponse changePassword(ChangePasswordRequest request) throws NonExistentException;
    GetUserWithAuthoritiesByLoginResponse getUserWithAuthoritiesByLogin(GetUserWithAuthoritiesByLoginRequest request) throws NonExistentException;
    GetUserWithAuthoritiesByIdResponse getUserWithAuthoritiesById(GetUserWithAuthoritiesByIdRequest request) throws NonExistentException;
    GetUserWithAuthoritiesResponse getUserWithAuthorities(GetUserWithAuthoritiesRequest request) throws NonExistentException;
}
