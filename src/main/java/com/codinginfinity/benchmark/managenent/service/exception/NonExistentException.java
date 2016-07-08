package com.codinginfinity.benchmark.managenent.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic exception which is thrown when a specified entity could not be retrieved from the repository and no specific
 * information needs to be returned up the call stack.
 *
 * @author Andrew Broekman
 * @author Fabio Loreggian
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED)
public class NonExistentException extends Exception {

    private static final long serialVersionUID = 1521974978161487658L;

    public NonExistentException() {
        super();
    }

    public NonExistentException(String message) {
        super(message);
    }

    public NonExistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentException(Throwable cause) {
        super(cause);
    }

    protected NonExistentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
