package com.codinginfinity.benchmark.management.service.reporting.exception;

/**
 * Created by andrew on 2016/08/25.
 */
public class ProcessingException extends Exception {

    private static final long serialVersionUID = -8963822569587568548L;

    public ProcessingException() {
        super();
    }

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessingException(Throwable cause) {
        super(cause);
    }

    protected ProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
