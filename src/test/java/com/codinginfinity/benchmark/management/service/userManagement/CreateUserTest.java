package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.managenent.ManagementApp;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.CreateUserRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;

/**
 * Created by andrew on 2016/06/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
public class CreateUserTest {

    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

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
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(user));
        thrown.expect(DuplicateUsernameException.class);
        thrown.expectMessage("Username already exists");

        userManagement.createUser(new CreateUserRequest("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com", Optional.empty()));
    }

    @Test
    public void createUserTest() throws DuplicateUsernameException {
        Mockito.when(userRepository.save((User)any())).thenAnswer(invocation -> {
            User user = (User)invocation.getArguments()[0];
            user.setId(12345L);
            return user;
        });
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("p@$$w0rd")).thenReturn("encodedpassword");

        User savedUser = userManagement.createUser(new CreateUserRequest("johndoe","p@$$w0rd","John", "Doe", "johndoe@example.com", Optional.empty())).getUser();
        assertEquals(new Long(12345), savedUser.getId());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("encodedpassword", savedUser.getPassword());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertFalse(savedUser.isActivated());
        assertNull(savedUser.getResetDate());
        assertNull(savedUser.getResetKey());
    }
}

