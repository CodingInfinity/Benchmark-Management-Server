package com.codinginfinity.benchmark.management.service.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown when a user tries to request a password reset on an email address not registered in the
 * system.
 *
 * @author Andrew Broekman
 * @author Fabio Loreggian
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason = "Email not registered")
public class EmailNotRegisteredException extends Exception {

    public EmailNotRegisteredException() {
        super();
    }

    public EmailNotRegisteredException(String message) {
        super(message);
    }

    public EmailNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotRegisteredException(Throwable cause) {
        super(cause);
    }

    protected EmailNotRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
