package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.NotAuthorizedException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CompletePasswordResetRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by andrew on 2016/06/20.
 */
public class CompeletePasswordResetTest extends AbstractTest {

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
    public void resetKeyNotInDatabaseTest() throws NotAuthorizedException {
        Mockito.when(userRepository.findOneByResetKey("0123456789")).thenReturn(Optional.empty());
        thrown.expect(NotAuthorizedException.class);
        thrown.expectMessage("Invalid reset key");

        userManagement.completePasswordReset(new CompletePasswordResetRequest("newP@$$w0rd", "0123456789"));
    }

    @Test
    public void resetKeyOlderThan24HoursTest() throws NotAuthorizedException {
        User user = new User();
        user.setResetKey("0123456789");
        user.setResetDate(ZonedDateTime.now().minusHours(25));

        Mockito.when(userRepository.findOneByResetKey("0123456789")).thenReturn(Optional.of(user));
        thrown.expect(NotAuthorizedException.class);
        thrown.expectMessage("Reset key expired");

        userManagement.completePasswordReset(new CompletePasswordResetRequest("newP@$$w0rd", "0123456789"));
    }

    @Test
    public void completePasswordResetTest() throws NotAuthorizedException {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(true);
        user.setResetDate(ZonedDateTime.now().minusMinutes(55));
        user.setResetKey("0123456789");

        Mockito.when(userRepository.findOneByResetKey("0123456789")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        String oldPassword = user.getPassword();

        User resetUser = userManagement.completePasswordReset(new CompletePasswordResetRequest("newP@$$w0rd", "0123456789")).getUser();

         /* Assert that only required properties on the object has changed, no more and no less */
        assertNotNull(resetUser);
        assertEquals(user.getUsername(), resetUser.getUsername());
        assertNotEquals(oldPassword, resetUser.getPassword());
        assertEquals(user.getFirstName(), resetUser.getFirstName());
        assertEquals(user.getLastName(), resetUser.getLastName());
        assertEquals(user.getEmail(), resetUser.getEmail());
        assertEquals(user.isActivated(), resetUser.isActivated());
        /* The properties below should be now different on the new entity */
        assertNull(resetUser.getResetKey());
        assertNull(resetUser.getResetDate());
    }
}
