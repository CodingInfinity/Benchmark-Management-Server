package com.codinginfinity.benchmark.managenent.service.userManagement.exceptions;

/**
 * Created by andrew on 2016/06/19.
 */
public class NotAuthorizedException extends Exception {

    private static final long serialVersionUID = 3586542544011106708L;

    public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorizedException(Throwable cause) {
        super(cause);
    }

    protected NotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
