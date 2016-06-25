package com.codinginfinity.benchmark.managenent.service.userManagement.exception;

/**
 * Created by andrew on 2016/06/20.
 */
public class NonExistentException extends InvalidRequestException {

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
