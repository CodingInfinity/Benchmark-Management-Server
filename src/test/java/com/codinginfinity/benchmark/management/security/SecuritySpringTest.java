package com.codinginfinity.benchmark.management.security;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created by andrew on 2016/07/06.
 */
@Configuration
public class SecuritySpringTest {

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutHandler();
    }

    @Bean
    public TokenStore tokenStore() {
        return Mockito.mock(TokenStore.class);
    }
}
