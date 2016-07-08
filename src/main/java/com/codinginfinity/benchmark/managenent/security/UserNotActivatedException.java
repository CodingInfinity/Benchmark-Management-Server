package com.codinginfinity.benchmark.managenent.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by andrew on 2016/06/15.
 */
public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
