package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.managenent.ManagementApp;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.UserNotActivatedException;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendPasswordResetMailResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.RequestPasswordResetRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
public class RequestPasswordResetTest {

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
    public void userNotRegisteredTest() throws NotAuthorizedException, UserNotActivatedException {
        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        thrown.expect(NotAuthorizedException.class);
        thrown.expectMessage("Invalid email address");

        userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com"));
    }

    @Test
    public void userNotActivatedTest() throws NotAuthorizedException, UserNotActivatedException {
        User user = new User();
        user.setActivated(false);

        when(userRepository.findOneByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        thrown.expect(UserNotActivatedException.class);
        thrown.expectMessage("User is not activated");

        userManagement.requestPasswordReset(new RequestPasswordResetRequest("johndoe@example.com"));
    }

    @Test
    public void userHasOutstandingResetRequests() throws NotAuthorizedException, UserNotActivatedException {
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
    public void resetUserPassword() throws NotAuthorizedException, UserNotActivatedException {
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
