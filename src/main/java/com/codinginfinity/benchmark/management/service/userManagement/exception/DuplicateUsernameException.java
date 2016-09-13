package com.codinginfinity.benchmark.management.service.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown when a user tries to register an username that already exists in the system.
 *
 * @author Andrew Broekman
 * @author Fabio Loreggian
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason = "Duplicate Username")
public class DuplicateUsernameException extends Exception {

    public DuplicateUsernameException() {
        super();
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsernameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
