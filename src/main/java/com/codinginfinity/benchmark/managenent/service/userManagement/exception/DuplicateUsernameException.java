package com.codinginfinity.benchmark.managenent.service.userManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by andrew on 2016/06/20.
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
