package com.codinginfinity.benchmark.management.test.security;

import com.codinginfinity.benchmark.management.repository.AuthorityRepository;
import com.codinginfinity.benchmark.management.repository.UserRepository;
import com.codinginfinity.benchmark.management.security.UserDetailsService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andrew on 2016/08/31.
 */
@Configuration
@Import(UserDetailsService.class)
public class SecurityConfiguration {

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public AuthorityRepository authorityRepository() {
        return Mockito.mock(AuthorityRepository.class);
    }
}
