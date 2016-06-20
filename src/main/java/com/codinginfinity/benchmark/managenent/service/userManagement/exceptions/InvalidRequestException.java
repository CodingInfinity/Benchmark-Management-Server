package com.codinginfinity.benchmark.managenent.service.userManagement.exceptions;

/**
 * Created by andrew on 2016/06/20.
 */
public class InvalidRequestException extends Exception {

    private static final long serialVersionUID = 3171150556823985352L;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(Throwable cause) {
        super(cause);
    }

    protected InvalidRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
