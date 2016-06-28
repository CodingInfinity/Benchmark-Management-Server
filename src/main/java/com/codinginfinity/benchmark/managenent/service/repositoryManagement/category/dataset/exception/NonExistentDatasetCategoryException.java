package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.exception;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;

/**
 * Created by andrew on 2016/06/28.
 */
public class NonExistentDatasetCategoryException extends NonExistentCategoryException {

    private static final long serialVersionUID = -5189023095959312238L;

    public NonExistentDatasetCategoryException() {
        super();
    }

    public NonExistentDatasetCategoryException(String message) {
        super(message);
    }

    public NonExistentDatasetCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentDatasetCategoryException(Throwable cause) {
        super(cause);
    }

    public NonExistentDatasetCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
