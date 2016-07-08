package com.codinginfinity.benchmark.managenent.service.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown when a user tries to request a password reset on a user which is not active.
 * system.
 *
 * @author Andrew Broekman
 * @author Fabio Loreggian
 *
 * @since 1.0.0
 * @version 1.0.0
 */

@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason = "User not activated")
public class UserNotActivatedException extends Exception {

    private static final long serialVersionUID = 1178029384021425673L;

    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotActivatedException(Throwable cause) {
        super(cause);
    }

    protected UserNotActivatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
