package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateUnmanagedUserRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/21.
 */
public class CreateUnmanagedUserTest extends AbstractTest {

    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Notification notification;

    @InjectMocks
    @Inject
    private UserManagement userManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void duplicateUsernameTest() throws DuplicateUsernameException {
        User user = new User();
        user.setUsername("johndoe");
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(user));
        thrown.expect(DuplicateUsernameException.class);
        thrown.expectMessage("Username already exists");

        userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com"));
    }

    @Test
    public void createUnmanagedUserTest() throws DuplicateUsernameException {
        when(userRepository.save((User)any())).thenAnswer(invocation -> {
            User user = (User)invocation.getArguments()[0];
            user.setId(12345L);
            return user;
        });
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
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
}