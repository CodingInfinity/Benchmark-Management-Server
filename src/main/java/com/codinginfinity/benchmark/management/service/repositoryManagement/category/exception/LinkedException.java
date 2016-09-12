package com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception;

/**
 * Created by andrew on 2016/09/12.
 */
public class LinkedException extends Exception {

    private static final long serialVersionUID = -3288206908751888297L;

    public LinkedException() {
    }

    public LinkedException(String message) {
        super(message);
    }

    public LinkedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkedException(Throwable cause) {
        super(cause);
    }

    public LinkedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
