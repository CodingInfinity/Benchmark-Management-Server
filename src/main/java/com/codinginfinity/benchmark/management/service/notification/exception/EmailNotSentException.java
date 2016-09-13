package com.codinginfinity.benchmark.management.service.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown when the system is unable to send an email due to any one or more reasons.
 *
 * @author Andrew Broekman
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED)
public class EmailNotSentException extends Exception {

    private static final long serialVersionUID = -4369243337098740377L;

    public EmailNotSentException() {
    }

    public EmailNotSentException(String message) {
        super(message);
    }

    public EmailNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotSentException(Throwable cause) {
        super(cause);
    }

    public EmailNotSentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
