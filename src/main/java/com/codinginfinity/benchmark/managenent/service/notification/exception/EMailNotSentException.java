package com.codinginfinity.benchmark.managenent.service.notification.exception;

/**
 * Created by andrew on 2016/06/25.
 */
public class EMailNotSentException extends Exception {

    private static final long serialVersionUID = -4369243337098740377L;

    public EMailNotSentException() {
    }

    public EMailNotSentException(String message) {
        super(message);
    }

    public EMailNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EMailNotSentException(Throwable cause) {
        super(cause);
    }

    public EMailNotSentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
