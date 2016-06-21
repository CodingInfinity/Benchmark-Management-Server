package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.managenent.ManagementApp;
import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exceptions.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.GetUserWithAuthoritiesByLoginRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
public class GetUserWithAuthoritiesByLoginTest {

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
    public void nonExistentUsernameTest() throws NonExistentException {
        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        thrown.expectMessage("User does not exist");
        userManagement.getUserWithAuthoritiesByLogin(new GetUserWithAuthoritiesByLoginRequest("johndoe"));
    }

    @Test
    public void getUserWithAuthoritiesByLoginTest() throws NonExistentException {
        User user = new User();
        user.setUsername("johndoe");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority(AuthoritiesConstants.USER));
        user.setAuthorities(authorities);

        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.of(user));
        User retrievedUser = userManagement.getUserWithAuthoritiesByLogin(new GetUserWithAuthoritiesByLoginRequest("johndoe")).getUser();
        assertEquals(user, retrievedUser);
    }
}
