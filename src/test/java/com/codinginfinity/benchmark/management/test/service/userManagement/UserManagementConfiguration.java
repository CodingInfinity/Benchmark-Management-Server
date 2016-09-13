package com.codinginfinity.benchmark.management.test.service.userManagement;

import com.codinginfinity.benchmark.management.repository.AuthorityRepository;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.service.notification.Notification;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagementImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by andrew on 2016/08/31.
 */
@Configuration
@Import(UserManagementImpl.class)
public class UserManagementConfiguration {

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public AuthorityRepository authorityRepository() {
        return Mockito.mock(AuthorityRepository.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Mockito.mock(PasswordEncoder.class);
    }

    @Bean
    public Notification notification() {
        return Mockito.mock(Notification.class);
    }
}
