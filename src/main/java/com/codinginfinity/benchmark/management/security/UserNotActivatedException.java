package com.codinginfinity.benchmark.management.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown if the user has not activated there account.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = -8399802749257003001L;

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
