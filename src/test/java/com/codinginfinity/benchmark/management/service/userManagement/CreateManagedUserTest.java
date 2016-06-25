package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendPasswordResetMailResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.EmailAlreadyExistsException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateManagedUserRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateUnmanagedUserRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by andrew on 2016/06/21.
 */
public class CreateManagedUserTest extends AbstractTest {

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
        userRepository = mock(UserRepository.class);
        initMocks(this);
    }

    @Test
    public void duplicateUsernameTest() throws DuplicateUsernameException, EmailAlreadyExistsException {
        User user = new User();
        user.setUsername("johndoe");
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(user));
        thrown.expect(DuplicateUsernameException.class);
        thrown.expectMessage("Username already exists");

        userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com"));
    }

    @Test
    public void duplicateEmailAddressTest() throws DuplicateUsernameException, EmailAlreadyExistsException {
        User user = new User();
        user.setUsername("johndoe");
        user.setEmail("johndoe@exampe.com");

        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepository.findOneByEmail("johndoe@exampe.com")).thenReturn(Optional.of(user));
        thrown.expect(EmailAlreadyExistsException.class);
        thrown.expectMessage("Email already exists");

        userManagement.createUnmanagedUser(new CreateUnmanagedUserRequest("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com"));
    }

    @Test
    public void createUnmanagedUserTest() throws DuplicateUsernameException, EmailAlreadyExistsException {
        when(userRepository.save((User)any())).thenAnswer(invocation -> {
            User user = (User)invocation.getArguments()[0];
            user.setId(12345L);
            return user;
        });
        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedpassword");
        when(notification.sendPasswordResetMail(any())).thenReturn(new SendPasswordResetMailResponse());

        Set<String> authorities = new HashSet<>();
        authorities.add(AuthoritiesConstants.USER);
        User savedUser = userManagement.createManagedUser(new CreateManagedUserRequest("johndoe","John", "Doe", "johndoe@example.com", authorities)).getUser();

        assertEquals(new Long(12345), savedUser.getId());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("encodedpassword", savedUser.getPassword());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertTrue(savedUser.isActivated());
        assertNotNull(savedUser.getResetDate());
        assertNotNull(savedUser.getResetKey());

        verify(notification, times(0)).sendPasswordResetMail(any());
        verify(notification, times(0)).sendCreationEmail(any());
        verify(notification, times(1)).sendActivationEmail(any());
        verify(notification, times(0)).sendEmail(any());
    }


}
