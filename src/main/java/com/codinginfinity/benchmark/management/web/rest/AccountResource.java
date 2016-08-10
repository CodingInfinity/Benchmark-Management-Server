package com.codinginfinity.benchmark.management.web.rest;

import com.codinginfinity.benchmark.management.domain.Profile;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.security.UserNotActivatedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.exception.*;
import com.codinginfinity.benchmark.management.service.userManagement.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Defines RESTful API endpoints for all management related to a specific user account.
 *
 * @see com.codinginfinity.benchmark.management.service.userManagement
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */


@RestController
@RequestMapping("/api")
@Slf4j
public class AccountResource {

    @Inject
    private UserManagement userManagement;

    /**
     * POST  /register : register the user.
     *
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) if the user is registered or 400 (Bad Request) if the login or e-mail is already in use
     */
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> registerAccount(@Valid @RequestBody CreateUnmanagedUserRequest request)
            throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        userManagement.createUnmanagedUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET  /activate : activate the registered user.
     *
     * @param key the activation key
     * @return the ResponseEntity with status 200 (OK) and the activated user in body, or status 500 (Internal Server Error) if the user couldn't be activated
     */
    @RequestMapping(value = "/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activateAccount(@RequestParam(value = "key") String key) throws NotAuthorizedException {
        User user = userManagement.activateRegistration(new ActivateRegistrationRequest(key)).getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET  /account : get the current user.
     *
     * @return the ResponseEntity with status 200 (OK) and the current user in body, or status 500 (Internal Server Error) if the user couldn't be returned
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getAccount() throws NonExistentException {
        User user = userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser();
        return new ResponseEntity<>(new Profile(user), HttpStatus.OK);
    }

    /**
     * POST  /account : update the current user information.
     *
     * @param request the current user information
     * @return the ResponseEntity with status 200 (OK), or status 400 (Bad Request) or 500 (Internal Server Error) if the user couldn't be updated
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAccount(@Valid @RequestBody UpdateUserRequest request)
            throws NotAuthorizedException, NonExistentException, EmailAlreadyExistsException {
        userManagement.updateUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST  /account/change_password : changes the current user's password
     *
     * @param request the new password
     * @return the ResponseEntity with status 200 (OK), or status 400 (Bad Request) if the new password is not strong enough
     */
    @RequestMapping(value = "/account/change_password",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request)
            throws NonExistentException{
        userManagement.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST   /account/reset_password/init : Send an e-mail to reset the password of the user
     *
     * @param request the HTTP request
     * @return the ResponseEntity with status 200 (OK) if the e-mail was sent, or status 400 (Bad Request) if the e-mail address is not registred
     */
    @RequestMapping(value = "/account/reset_password/init",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> requestPasswordReset(@RequestBody RequestPasswordResetRequest request)
            throws EmailNotRegisteredException, NotAuthorizedException, UserNotActivatedException,
            EmailNotSentException {
        userManagement.requestPasswordReset(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST   /account/reset_password/finish : Finish to reset the password of the user
     *
     * @param request the generated key and the new password
     * @return the ResponseEntity with status 200 (OK) if the password has been reset,
     * or status 400 (Bad Request) or 500 (Internal Server Error) if the password could not be reset
     */
    @RequestMapping(value = "/account/reset_password/finish",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> finishPasswordReset(@RequestBody CompletePasswordResetRequest request)
            throws NotAuthorizedException {
        userManagement.completePasswordReset(request);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
