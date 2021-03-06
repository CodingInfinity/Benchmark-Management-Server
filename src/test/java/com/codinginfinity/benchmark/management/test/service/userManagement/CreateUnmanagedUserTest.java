package com.codinginfinity.benchmark.management.test.service.userManagement;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.management.service.userManagement.exception.DuplicateUsernameException;
import com.codinginfinity.benchmark.management.service.userManagement.exception.EmailAlreadyExistsException;
import com.codinginfinity.benchmark.management.service.userManagement.request.CreateUnmanagedUserRequest;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/21.
 */
public class CreateUnmanagedUserTest extends AbstractCreateUserTest {

    @Test
    public void createUnmanagedUserTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        when(userRepository.save((User)any())).thenAnswer(invocation -> {
            User user = (User)invocation.getArguments()[0];
            user.setId(12345L);
            return user;
        });
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("p@$$w0rd")).thenReturn("encodedpassword");
        when(notification.sendCreationEmail(any())).thenReturn(new SendCreationEmailResponse());

        User savedUser = userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest("johndoe","p@$$w0rd","John", "Doe", "johndoe@example.com")).getUser();

        assertEquals(new Long(12345), savedUser.getId());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("encodedpassword", savedUser.getPassword());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertFalse(savedUser.isActivated());
        assertNull(savedUser.getResetDate());
        assertNull(savedUser.getResetKey());

        verify(notification, times(0)).sendPasswordResetMail(any());
        verify(notification, times(1)).sendCreationEmail(any());
        verify(notification, times(0)).sendActivationEmail(any());
        verify(notification, times(0)).sendEmail(any());
    }

    @Override
    public void duplicateUsername(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest(username, password, firstName, lastName, email));
    }

    @Override
    public void duplicateEmailAddress(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EmailNotSentException {
        userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest(username, password, firstName, lastName, email));
    }
}