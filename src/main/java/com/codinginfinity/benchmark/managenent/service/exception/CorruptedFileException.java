package com.codinginfinity.benchmark.managenent.service.exception;

/**
 * Created by andrew on 2016/07/08.
 */
public class CorruptedFileException extends Exception {

    private static final long serialVersionUID = -4991693655276047096L;

    public CorruptedFileException() {
        super();
    }

    public CorruptedFileException(String message) {
        super(message);
    }

    public CorruptedFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CorruptedFileException(Throwable cause) {
        super(cause);
    }

    protected CorruptedFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
