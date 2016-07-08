package com.codinginfinity.benchmark.managenent.service.exception;

/**
 * Created by andrew on 2016/07/08.
 */
public class FileFormatNotSupportedException extends Exception {

    private static final long serialVersionUID = -1592463924778791594L;

    public FileFormatNotSupportedException() {
        super();
    }

    public FileFormatNotSupportedException(String message) {
        super(message);
    }

    public FileFormatNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileFormatNotSupportedException(Throwable cause) {
        super(cause);
    }

    protected FileFormatNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
