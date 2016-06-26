package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.DeleteUserRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrew on 2016/06/20.
 */
public class DeleteUserTest extends AbstractTest {

    private UserRepository userRepository;

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
    public void deleteNonExistentUserTest() throws NonExistentException {
        User user = new User();
        user.setUsername("johndoen");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        thrown.expectMessage("User does not exist");
        userManagement.deleteUser(new DeleteUserRequest("johndoe"));
    }

    @Test
    public void deleteUser() throws NonExistentException {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(false);
        user.setResetDate(null);
        user.setResetKey(null);

        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(user));

        User deletedUser = userManagement.deleteUser(new DeleteUserRequest("johndoe")).getUser();
        assertNotNull(deletedUser);
        assertEquals(user, deletedUser);
    }
}
