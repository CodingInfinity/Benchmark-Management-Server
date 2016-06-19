package com.codinginfinity.benchmark.managenent.service.userManagement;

/**
 * Created by andrew on 2016/06/19.
 */
public class UserNotActivatedException extends Exception {

    private static final long serialVersionUID = 1178029384021425673L;

    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotActivatedException(Throwable cause) {
        super(cause);
    }

    protected UserNotActivatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
