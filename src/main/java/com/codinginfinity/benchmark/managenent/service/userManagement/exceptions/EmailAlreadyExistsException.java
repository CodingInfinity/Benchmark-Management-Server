package com.codinginfinity.benchmark.managenent.service.userManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fabio on 2016/06/24.
 */
@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason = "Email Already Registered")
public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException() {
        super();
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected EmailAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}



