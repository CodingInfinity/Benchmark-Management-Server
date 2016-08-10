package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesByIdRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/21.
 */
public class GetUserWithAuthoritiesByIdTest extends AbstractTest {

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
        when(userRepository.findOneById(anyLong())).thenReturn(Optional.empty());
        thrown.expect(NonExistentException.class);
        thrown.expectMessage("User does not exist");
        userManagement.getUserWithAuthoritiesById(new GetUserWithAuthoritiesByIdRequest(12345L));
    }

    @Test
    public void getUserWithAuthoritiesByLoginTest() throws NonExistentException {
        User user = new User();
        user.setId(12345L);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority(AuthoritiesConstants.USER));
        user.setAuthorities(authorities);

        when(userRepository.findOneById(12345L)).thenReturn(Optional.of(user));
        User retrievedUser = userManagement.getUserWithAuthoritiesById(new GetUserWithAuthoritiesByIdRequest(12345L)).getUser();
        assertEquals(user, retrievedUser);
        assertEquals(1, retrievedUser.getAuthorities().size());
    }
}

