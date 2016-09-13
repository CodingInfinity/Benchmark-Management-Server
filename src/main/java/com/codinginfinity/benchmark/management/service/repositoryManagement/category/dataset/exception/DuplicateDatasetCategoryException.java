package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.exception;

import com.codinginfinity.benchmark.management.service.repositoryManagement.category.exception.DuplicateCategoryException;

/**
 * Created by andrew on 2016/06/28.
 */
public class DuplicateDatasetCategoryException extends DuplicateCategoryException {

    private static final long serialVersionUID = 4109152317128624654L;

    public DuplicateDatasetCategoryException() {
        super();
    }

    public DuplicateDatasetCategoryException(String message) {
        super(message);
    }

    public DuplicateDatasetCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateDatasetCategoryException(Throwable cause) {
        super(cause);
    }

    protected DuplicateDatasetCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
