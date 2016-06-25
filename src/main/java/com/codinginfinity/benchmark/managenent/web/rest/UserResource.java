package com.codinginfinity.benchmark.managenent.web.rest;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.service.notification.exception.EMailNotSentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.EmailAlreadyExistsException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateManagedUserRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.DeleteUserRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.GetUserWithAuthoritiesByLoginRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.UpdateUserRequest;
import com.codinginfinity.benchmark.managenent.web.rest.dto.UserDTO;
import com.codinginfinity.benchmark.managenent.web.rest.utils.PaginationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrew on 2016/06/20.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserResource {

    @Inject
    private UserManagement userManagement;

    @Inject
    private UserRepository userRepository;

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     * </p>
     *
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) and with body the new user
     * @throws URISyntaxException if the Location URI syntaxt is incorrect
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createUser(@RequestBody CreateManagedUserRequest request)
            throws URISyntaxException, DuplicateUsernameException, EmailAlreadyExistsException,
            EMailNotSentException {
        log.debug("REST request to save User : {}", request);
        User user = userManagement.createManagedUser(request).getUser();
        return ResponseEntity.created(new URI("/api/users/" + user.getUsername())).body(new UserDTO(user));
    }

    /**
     * PUT  /users : Updates an existing User.
     *
     * @param request the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserRequest request)
            throws NotAuthorizedException, NonExistentException {
        log.debug("REST request to update User : {}", request);
        User user = userManagement.updateUser(request).getUser();
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    /**
     * GET  /users : get all users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     * @throws URISyntaxException if the pagination headers couldn't be generated
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable)
            throws URISyntaxException {
        Page<User> page = userRepository.findAll(pageable);
        List<UserDTO> managedUserDTOs = page.getContent().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtils.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(managedUserDTOs, headers, HttpStatus.OK);
    }

    /**
     * GET  /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/users/{login}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) throws NonExistentException {
        log.debug("REST request to get User : {}", login);
        User user =  userManagement.getUserWithAuthoritiesByLogin(new GetUserWithAuthoritiesByLoginRequest(login)).getUser();
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    /**
     * DELETE  USER :login : delete the "login" User.
     *
     * @param login the login of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/users/{login}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) throws NonExistentException {
        log.debug("REST request to delete User: {}", login);
        userManagement.deleteUser(new DeleteUserRequest(login));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}