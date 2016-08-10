package com.codinginfinity.benchmark.management.service.repositoryManagement.exception;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;

/**
 * Created by reinhardt on 2016/06/29.
 */
public class NonExistentRepoEntityException extends NonExistentException {
    public NonExistentRepoEntityException() {
    }

    public NonExistentRepoEntityException(String message) {
        super(message);
    }

    public NonExistentRepoEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentRepoEntityException(Throwable cause) {
        super(cause);
    }

    public NonExistentRepoEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
