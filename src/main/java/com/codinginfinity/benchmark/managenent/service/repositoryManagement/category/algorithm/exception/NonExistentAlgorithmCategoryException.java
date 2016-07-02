package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;

/**
 * Created by andrew on 2016/06/28.
 */
public class NonExistentAlgorithmCategoryException extends NonExistentCategoryException {

    private static final long serialVersionUID = -4394162653595797429L;

    public NonExistentAlgorithmCategoryException() {
        super();
    }

    public NonExistentAlgorithmCategoryException(String message) {
        super(message);
    }

    public NonExistentAlgorithmCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentAlgorithmCategoryException(Throwable cause) {
        super(cause);
    }

    public NonExistentAlgorithmCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
