package com.codinginfinity.benchmark.management.test.service.userManagement;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.SecurityUtils;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.request.ChangePasswordRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by andrew on 2016/08/31.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(SecurityUtils.class)
@SpringApplicationConfiguration(UserManagementConfiguration.class)
public class ChangePasswordTest {

    @Inject
    private UserManagement userManagement;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void changePasswordTest() throws NonExistentException {
        PowerMockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getCurrentUsername()).thenReturn("johndoe");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(createUser()));
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        userManagement.changePassword(new ChangePasswordRequest("newP@$$w0rd"));
    }

    @Test
    public void changePasswordNonExistentExceptionTest() throws NonExistentException {
        PowerMockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getCurrentUsername()).thenReturn("johndoe");
        Mockito.when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());

        thrown.expect(NonExistentException.class);
        userManagement.changePassword(new ChangePasswordRequest("test"));
    }

    private User createUser() {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("pa$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        return user;
    }


}
