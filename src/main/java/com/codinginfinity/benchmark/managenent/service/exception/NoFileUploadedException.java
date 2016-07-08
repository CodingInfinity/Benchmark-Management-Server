package com.codinginfinity.benchmark.managenent.service.exception;

/**
 * Created by andrew on 2016/07/08.
 */
public class NoFileUploadedException extends Exception {

    private static final long serialVersionUID = -6864671225311441454L;

    public NoFileUploadedException() {
        super();
    }

    public NoFileUploadedException(String message) {
        super(message);
    }

    public NoFileUploadedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFileUploadedException(Throwable cause) {
        super(cause);
    }

    protected NoFileUploadedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
