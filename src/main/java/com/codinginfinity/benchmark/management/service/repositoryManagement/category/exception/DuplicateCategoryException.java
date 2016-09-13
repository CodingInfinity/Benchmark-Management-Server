package com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception;

/**
 * Created by andrew on 2016/06/26.
 */
public class DuplicateCategoryException extends Exception {

    private static final long serialVersionUID = 1354219905232901476L;

    public DuplicateCategoryException() {
        super();
    }

    public DuplicateCategoryException(String message) {
        super(message);
    }

    public DuplicateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCategoryException(Throwable cause) {
        super(cause);
    }

    protected DuplicateCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
