package com.codinginfinity.benchmark.managenent.service.userManagement.exceptions;

/**
 * Created by andrew on 2016/06/20.
 */
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
