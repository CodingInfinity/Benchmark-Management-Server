package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.managenent.ManagementApp;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.ActivateRegistrationRequest;
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
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by andrew on 2016/06/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
public class ActivateRegistrationTest {

    @Mock
    private UserRepository userRepository;

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
        Mockito.when(userRepository.findOneByActivationKey("0123456789")).thenReturn(Optional.empty());
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

        Mockito.when(userRepository.findOneByActivationKey("0123456789")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
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
