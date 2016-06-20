package com.codinginfinity.benchmark.management.security;

import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.security.SecurityUtils;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by andrew on 2016/06/20.
 */
public class SecurityUtilsTest {

    @Test
    public void getCurrentUserUsernameTest() {
        SecurityContext securityContext = createSpringSecurityContext("JohnDoe", "pa$$w0rd");

        SecurityContextHolder.setContext(securityContext);
        String username = SecurityUtils.getCurrentUsername();
        assertEquals(username, "JohnDoe");
    }

    @Test
    public void isAuthenticatedTest() {
        SecurityContext securityContext = createSpringSecurityContext("JohnDoe", "pa$$w0rd");

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);

        SecurityContextHolder.setContext(securityContext);
        isAuthenticated = SecurityUtils.isAuthenticated();
        assertTrue(isAuthenticated);
    }

    @Test
    public void anonymousCantBeAuthenticatedTest() {
        SecurityContext securityContext = createSpringSecurityContext("anonymous", "anonymous", AuthoritiesConstants.ANONYMOUS);
        SecurityContextHolder.setContext(securityContext);
        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);
    }

    private SecurityContext createSpringSecurityContext(String username, String password, String ...roles) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (roles.length > 0) {
            for (String role: roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("JohnDoe", "pa$$w0rd", authorities));
        return securityContext;
    }
}
