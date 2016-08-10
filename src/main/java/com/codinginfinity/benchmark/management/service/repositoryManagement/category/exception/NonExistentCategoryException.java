package com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;

/**
 * Created by andrew on 2016/06/28.
 */
public class NonExistentCategoryException extends NonExistentException {

    private static final long serialVersionUID = -3119073864562623095L;

    public NonExistentCategoryException() {
    }

    public NonExistentCategoryException(String message) {
        super(message);
    }

    public NonExistentCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentCategoryException(Throwable cause) {
        super(cause);
    }

    public NonExistentCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
