package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.response.SendPasswordResetMailResponse;
import com.codinginfinity.benchmark.management.service.userManagement.exception.DuplicateUsernameException;
import com.codinginfinity.benchmark.management.service.userManagement.exception.EmailAlreadyExistsException;
import com.codinginfinity.benchmark.management.service.userManagement.request.CreateManagedUserRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/21.
 */
public class CreateManagedUserTest extends AbstractCreateUserTest {

    @Before
    public void setUp() throws Exception {
        when(userRepository.save((User)any())).thenAnswer(invocation -> {
            User user = (User)invocation.getArguments()[0];
            user.setId(12345L);
            return user;
        });
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedpassword");
        when(notification.sendPasswordResetMail(any())).thenReturn(new SendPasswordResetMailResponse());
        Authority userRole = new Authority(AuthoritiesConstants.USER);
        when(authorityRepository.findOne(AuthoritiesConstants.USER)).thenReturn(userRole);
    }

    @Test
    public void createManagedUserTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {

        Set<String> authorities = new HashSet<>();
        authorities.add(AuthoritiesConstants.USER);
        User savedUser = userManagement.createManagedUser(new CreateManagedUserRequest("johndoe","John", "Doe", "johndoe@example.com", authorities)).getUser();
        verifyUser(savedUser);
    }

    @Test
    public void createManagedUserWithNoRolesTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {

        User savedUser = userManagement.createManagedUser(new CreateManagedUserRequest("johndoe","John", "Doe", "johndoe@example.com", null)).getUser();
        verifyUser(savedUser);
    }

    @Test
    public void createManagedUserWithEmptyRolesTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {

        Set<String> authorities = new HashSet<>();
        User savedUser = userManagement.createManagedUser(new CreateManagedUserRequest("johndoe","John", "Doe", "johndoe@example.com", authorities)).getUser();
        verifyUser(savedUser);

    }

    private void verifyUser(User user) throws EmailNotSentException {
        assertEquals(new Long(12345), user.getId());
        assertEquals("johndoe", user.getUsername());
        assertEquals("encodedpassword", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("johndoe@example.com", user.getEmail());
        assertTrue(user.isActivated());
        assertNotNull(user.getResetDate());
        assertNotNull(user.getResetKey());
        user.getAuthorities().stream().forEach(role -> {
            assert authorityRepository.findOne(role.getName()) != null;
        });

        verify(notification, times(0)).sendPasswordResetMail(any());
        verify(notification, times(0)).sendCreationEmail(any());
        verify(notification, times(1)).sendActivationEmail(any());
        verify(notification, times(0)).sendEmail(any());
    }

    @Override
    public void duplicateUsername(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        userManagement.createManagedUser(new CreateManagedUserRequest(username, firstName, lastName, email, null));
    }

    @Override
    public void duplicateEmailAddress(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        userManagement.createManagedUser(new CreateManagedUserRequest(username, firstName, lastName, email, null));
    }
}
