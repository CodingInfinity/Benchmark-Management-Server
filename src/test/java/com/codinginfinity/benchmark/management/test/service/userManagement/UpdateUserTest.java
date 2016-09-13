package com.codinginfinity.benchmark.management.test.service.userManagement;

import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.SecurityUtils;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.exception.EmailAlreadyExistsException;
import com.codinginfinity.benchmark.management.service.userManagement.request.UpdateUserRequest;
import com.codinginfinity.benchmark.management.service.userManagement.response.UpdateUserResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by andrew on 2016/08/31.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(SecurityUtils.class)
@SpringApplicationConfiguration(UserManagementConfiguration.class)
public class UpdateUserTest {

    @Inject
    private UserManagement userManagement;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void updateUserNonExistentExceptionTest() throws NonExistentException, EmailAlreadyExistsException {
        PowerMockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getCurrentUsername()).thenReturn("johndoe");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());

        thrown.expect(NonExistentException.class);
        userManagement.updateUser(new UpdateUserRequest("Jane", "Doe", "janedoe@example.com"));
    }

    @Test
    public void updateUserEmailAlreadyExistsExceptionTest() throws NonExistentException, EmailAlreadyExistsException {
        PowerMockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getCurrentUsername()).thenReturn("johndoe");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(createJohn(null)));
        Mockito.when(userRepository.findOneByEmail("janedoe@example.com")).thenReturn(Optional.of(createJane()));

        thrown.expect(EmailAlreadyExistsException.class);
        userManagement.updateUser(new UpdateUserRequest("Jane", "Doe", "janedoe@example.com"));
    }

    @Test
    public void updateUserTest() throws NonExistentException, EmailAlreadyExistsException {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_TEST"));

        PowerMockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getCurrentUsername()).thenReturn("johndoe");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(createJohn(authorities)));
        Mockito.when(userRepository.findOneByEmail("janedoe@example.com")).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        UpdateUserResponse response = userManagement.updateUser(new UpdateUserRequest("Jane", "Doe", "janedoe@example.com"));
        Assert.assertEquals(new Long(1L), response.getUser().getId());
        Assert.assertEquals("Jane", response.getUser().getFirstName());
        Assert.assertEquals("Doe", response.getUser().getLastName());
        Assert.assertEquals("janedoe@example.com", response.getUser().getEmail());
        Assert.assertEquals("pa$$w0rd", response.getUser().getPassword());
        Assert.assertEquals(authorities, response.getUser().getAuthorities());
        Assert.assertEquals(true, response.getUser().isActivated());
        Assert.assertEquals("", response.getUser().getActivationKey());
        Assert.assertEquals(null, response.getUser().getResetDate());
        Assert.assertEquals("", response.getUser().getResetKey());

    }

    private User createJohn(Set<Authority> authorities) {
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        user.setPassword("pa$$w0rd");
        user.setAuthorities(authorities);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(true);
        user.setActivationKey("");
        user.setResetDate(null);
        user.setResetKey("");
        return user;
    }

    private User createJane() {
        User user = new User();
        user.setUsername("janedoe");
        user.setPassword("jpa$$w0rd");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("janedoe@example.com");
        return user;
    }
}
