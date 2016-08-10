package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.UserNotActivatedException;
import com.codinginfinity.benchmark.management.service.notification.Notification;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.response.SendPasswordResetMailResponse;
import com.codinginfinity.benchmark.management.service.userManagement.exception.EmailNotRegisteredException;
import com.codinginfinity.benchmark.management.service.userManagement.exception.NotAuthorizedException;
import com.codinginfinity.benchmark.management.service.userManagement.request.RequestPasswordResetRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/19.
 */
public class RequestPasswordResetTest extends AbstractTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Notification notification;

    @InjectMocks
    @Inject
    private UserManagement userManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userNotRegisteredTest() throws NotAuthorizedException, UserNotActivatedException, EmailNotRegisteredException, EmailNotSentException {
        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        thrown.expect(EmailNotRegisteredException.class);
        thrown.expectMessage("Invalid email address");

        userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com"));
    }

    @Test
    public void userNotActivatedTest() throws NotAuthorizedException, UserNotActivatedException, EmailNotRegisteredException, EmailNotSentException {
        User user = new User();
        user.setActivated(false);

        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        thrown.expect(UserNotActivatedException.class);
        thrown.expectMessage("User is not activated");

        userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com"));
    }

    @Test
    public void userHasOutstandingResetRequests() throws NotAuthorizedException, UserNotActivatedException, EmailNotRegisteredException, EmailNotSentException {
        User user = new User();
        user.setActivated(true);
        user.setResetDate(ZonedDateTime.now());
        user.setResetKey("1234567890");

        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        thrown.expect(NotAuthorizedException.class);
        thrown.expectMessage("User has outstanding reset requests");

        userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com"));
    }

    @Test
    public void resetUserPassword() throws NotAuthorizedException, UserNotActivatedException, EmailNotRegisteredException, MessagingException, EmailNotSentException {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(true);
        user.setResetDate(null);
        user.setResetKey(null);

        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(notification.sendPasswordResetMail(any())).thenReturn(new SendPasswordResetMailResponse());
        Optional<User> resetUser = userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com")).getUser();

        /* Assert that only required properties on the object has changed, no more and no less */
        assertTrue(resetUser.isPresent());
        assertEquals(user.getUsername(), resetUser.get().getUsername());
        assertEquals(user.getPassword(), resetUser.get().getPassword());
        assertEquals(user.getFirstName(), resetUser.get().getFirstName());
        assertEquals(user.getLastName(), resetUser.get().getLastName());
        assertEquals(user.getEmail(), resetUser.get().getEmail());
        assertEquals(user.isActivated(), resetUser.get().isActivated());
        /* The properties below should be now different on the new entity */
        assertNotNull(resetUser.get().getResetKey());
        assertNotNull(resetUser.get().getResetDate());
        assertTrue(ZonedDateTime.now().minusHours(1).isBefore(resetUser.get().getResetDate()));
        verify(notification, times(1)).sendPasswordResetMail(any());
        verify(notification, times(0)).sendCreationEmail(any());
        verify(notification, times(0)).sendActivationEmail(any());
        verify(notification, times(0)).sendEmail(any());
    }
}
