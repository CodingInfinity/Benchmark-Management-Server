package com.codinginfinity.benchmark.management.test.security;

import com.codinginfinity.benchmark.management.ManagementApp;
import com.codinginfinity.benchmark.management.domain.Authority;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.AuthorityRepository;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.userManagement.exception.UserNotActivatedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrew on 2016/06/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
@Transactional
public class UserDetailsServiceIT {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Test
    public void loadValidUserByUsernameTest() {

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.USER));

        User user = createUser(authorities, true);

        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername("johndoe");
        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());
        assertEquals(userDetails.getAuthorities().size(), user.getAuthorities().size());
        userDetails.getAuthorities().stream().forEach(authority -> {
            assertTrue(authorities.contains(new Authority(authority.getAuthority())));
        });
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadNonExistentUserTest() {
        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername("johndoe");
    }

    @Test(expected = UserNotActivatedException.class)
    public void userNotActivatedTest() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.USER));

        User user = createUser(authorities, false);
        userRepository.save(user);

        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername("johndoe");
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
