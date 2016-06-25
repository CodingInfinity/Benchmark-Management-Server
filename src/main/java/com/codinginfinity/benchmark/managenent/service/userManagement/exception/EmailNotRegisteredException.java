package com.codinginfinity.benchmark.managenent.service.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by andrew on 2016/06/20.
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
