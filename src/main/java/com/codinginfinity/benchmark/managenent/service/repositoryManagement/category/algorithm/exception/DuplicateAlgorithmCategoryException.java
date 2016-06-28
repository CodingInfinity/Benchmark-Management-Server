package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.exception;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;

/**
 * Created by andrew on 2016/06/28.
 */
public class DuplicateAlgorithmCategoryException extends DuplicateCategoryException {

    private static final long serialVersionUID = -8567711230165039906L;

    public DuplicateAlgorithmCategoryException() {
    }

    public DuplicateAlgorithmCategoryException(String message) {
        super(message);
    }

    public DuplicateAlgorithmCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAlgorithmCategoryException(Throwable cause) {
        super(cause);
    }

    public DuplicateAlgorithmCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
