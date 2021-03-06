package com.codinginfinity.benchmark.management.test.service.userManagement;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.service.notification.Notification;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.exception.NotAuthorizedException;
import com.codinginfinity.benchmark.management.service.userManagement.request.ActivateRegistrationRequest;
import com.codinginfinity.benchmark.management.test.AbstractTest;
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
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/20.
 */
public class ActivateRegistrationTest extends AbstractTest {

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
    }

}
