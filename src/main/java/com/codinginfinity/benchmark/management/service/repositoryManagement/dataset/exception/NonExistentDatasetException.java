package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.exception;

import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by reinhardt on 2016/06/29.
 */

/**
 * Exception which is throw when an attempt is made to access a Dataset that does not exist
 *
 * @author Reinhardt Cromhout
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED)
public class NonExistentDatasetException extends NonExistentRepoEntityException {
    public NonExistentDatasetException() {
    }

    public NonExistentDatasetException(String message) {
        super(message);
    }

    public NonExistentDatasetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentDatasetException(Throwable cause) {
        super(cause);
    }

    public NonExistentDatasetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
