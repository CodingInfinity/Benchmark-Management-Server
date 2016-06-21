package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.ActivateRegistrationRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/20.
 */
public class ActivateRegistrationTest extends AbstractTest{

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
    public void invalidActivationKeyTest() throws NotAuthorizedException {
        when(userRepository.findOneByActivationKey("0123456789")).thenReturn(Optional.empty());
        thrown.expect(NotAuthorizedException.class);
        thrown.expectMessage("Invalid activation key");

        userManagement.activateRegistration(new ActivateRegistrationRequest("0123456789"));
    }

    @Test
    public void activateRegistrationTest() throws NotAuthorizedException {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(false);
        user.setResetDate(null);
        user.setResetKey(null);

        when(userRepository.findOneByActivationKey("0123456789")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(notification.sendActivationEmail(any())).thenReturn(new SendActivationEmailResponse());
        User resetUser = userManagement.activateRegistration(new ActivateRegistrationRequest("0123456789")).getUser();

        /* Assert that only required properties on the object has changed, no more and no less */
        assertNotNull(resetUser);
        assertEquals(user.getUsername(), resetUser.getUsername());
        assertEquals(user.getPassword(), resetUser.getPassword());
        assertEquals(user.getFirstName(), resetUser.getFirstName());
        assertEquals(user.getLastName(), resetUser.getLastName());
        assertEquals(user.getEmail(), resetUser.getEmail());
        assertEquals(user.isActivated(), resetUser.isActivated());
        assertNull(resetUser.getResetKey());
        assertNull(resetUser.getResetDate());
        verify(notification, times(0)).sendPasswordResetMail(any());
        verify(notification, times(0)).sendCreationEmail(any());
        verify(notification, times(1)).sendActivationEmail(any());
        verify(notification, times(0)).sendEmail(any());
    }

}
