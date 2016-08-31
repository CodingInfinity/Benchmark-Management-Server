package com.codinginfinity.benchmark.management.test.security;

import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.AuthorityRepository;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrew on 2016/06/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SecurityConfiguration.class)
public class UserDetailsServiceTest {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void loadValidUserByUsernameTest() {

        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_TEST"));

        User user = createUser(authorities, true);

        Mockito.when(userRepository.findOneByUsername(Mockito.anyString())).thenReturn(Optional.of(user));

        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername("johndoe");
        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());
        assertEquals(userDetails.getAuthorities().size(), user.getAuthorities().size());
        userDetails.getAuthorities().stream().forEach(authority -> {
            assertTrue(authorities.contains(new Authority(authority.getAuthority())));
        });
    }

    @Test
    public void loadNonExistentUserTest() {
        Mockito.when(userRepository.findOneByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        thrown.expect(org.springframework.security.core.userdetails.UsernameNotFoundException.class);

        userDetailsService.loadUserByUsername("johndoe");
    }

    @Test
    public void userNotActivatedTest() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.USER));

        User user = createUser(authorities, false);
        userRepository.save(user);

        Mockito.when(userRepository.findOneByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        thrown.expect(com.codinginfinity.benchmark.management.security.UserNotActivatedException.class);

        userDetailsService.loadUserByUsername("johndoe");
    }

    private User createUser(Set<Authority> authorities, boolean activated) {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("pa$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(activated);
        user.setAuthorities(authorities);
        userRepository.save(user);
        return user;
    }
}
