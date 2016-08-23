package com.codinginfinity.benchmark.management.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Utility class to assist in easier retrieval of security information from the
 * Spring Security context.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the username of the user in the security context.
     *
     * @return String with the username of current user in context
     * @since 1.0.0
     */
    public static String getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                return (String) authentication.getPrincipal();
            }
        }
        return null;
    }

    /**
     * Check if the user in the security context is authenticated.
     *
     * @return True if the user in the context is authenticated.
     * @since 1.0.0
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() != null) {
            Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
            if (authorities.size() > 0) {
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the user in the security context has a certain role.
     *
     * @param authority The authority we want to enquire about on current user.
     * @return True if the user in the context has a role of {@param authority}.
     * @since 1.0.0
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }
}
