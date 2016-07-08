package com.codinginfinity.benchmark.management.security;

import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.managenent.security.SecurityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static net.trajano.commons.testing.UtilityClassTestUtil.assertUtilityClassWellDefined;
import static org.junit.Assert.*;

/**
 * Created by andrew on 2016/06/20.
 */
public class SecurityUtilsTest {

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void utilityClassWellDefinedTest() {
        assertUtilityClassWellDefined(SecurityUtils.class);
    }

    @Test
    public void getCurrentUserUsernameEmptyContextTest() {
        String username = SecurityUtils.getCurrentUsername();
        assertNull(username);
    }

    @Test
    public void getCurrentUserUsernameStringTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("JohnDoe", "pa$$w0rd");
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        String username = SecurityUtils.getCurrentUsername();
        assertEquals(username, "JohnDoe");
    }

    @Test
    public void getCurrentUserUsernamePrincipalTest() {
        Authentication authentication = createAuthenticatedUserDetailsPrincipal("JohnDoe", "pa$$w0rd", AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        String username = SecurityUtils.getCurrentUsername();
        assertEquals(username, "JohnDoe");
    }

    @Test
    public void getCurrentUserUsernameIncompatiblePrincipalTest() {
        Authentication authentication = createAuthenticatedStringPrincipal(null, null);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        String username = SecurityUtils.getCurrentUsername();
        assertNull(username);
    }

    @Test
    public void isAuthenticatedEmptyContextTest() {
        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);
    }

    @Test
    public void isAuthenticatedEmptyRoleTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("JohnDoe", "pa$$w0rd");
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);
    }

    @Test
    public void isAuthenticatedAnonymousRoleTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("", "", AuthoritiesConstants.ANONYMOUS);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);
    }

    @Test
    public void isAuthenticatedTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("JohnDoe", "pa$$w0rd", AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertTrue(isAuthenticated);
    }

    @Test
    public void anonymousCantBeAuthenticatedTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("anonymous", "anonymous", AuthoritiesConstants.ANONYMOUS);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean isAuthenticated = SecurityUtils.isAuthenticated();
        assertFalse(isAuthenticated);
    }

    @Test
    public void isCurrentUserInRoleEmptyContextTest() {
        boolean hasRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        assertFalse(hasRole);
    }

    @Test
    public void isCurrentUserInRoleIncompatibleTest() {
        Authentication authentication = createAuthenticatedStringPrincipal("JohnDoe", "pa$$w0rd", AuthoritiesConstants.USER);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean hasRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        assertFalse(hasRole);
    }

    @Test
    public void isCurrentUserInRoleFalseTest() {
        Authentication authentication = createAuthenticatedUserDetailsPrincipal("JohnDoe", "pa$$w0rd", AuthoritiesConstants.USER);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean hasRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        assertFalse(hasRole);
    }

    @Test
    public void isCurrentUserInRoleTrueTest() {
        Authentication authentication = createAuthenticatedUserDetailsPrincipal("JohnDoe", "pa$$w0rd", AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN);
        SecurityContext securityContext = createSpringSecurityContext(authentication);
        SecurityContextHolder.setContext(securityContext);

        boolean hasRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        assertTrue(hasRole);
    }

    private Authentication createAuthenticatedStringPrincipal(String username, String password, String ...roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles.length > 0) {
            for (String role: roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return new TestingAuthenticationToken(username, password, authorities);
    }

    private Authentication createAuthenticatedUserDetailsPrincipal(String username, String password, String ...roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles.length > 0) {
            for (String role: roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        UserDetails userDetails = new User(username, password, authorities);
        return new TestingAuthenticationToken(userDetails, null);
    }

    private SecurityContext createSpringSecurityContext(Authentication authentication) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        return securityContext;
    }

}
