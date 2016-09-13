package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm.exception;

/**
 * Created by reinhardt on 2016/06/26.
 */

import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is throw when an attempt is made to access an Algorithm that does not exist
 *
 * @author Reinhardt Cromhout
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED)
public class NonExistentAlgorithmException extends NonExistentRepoEntityException {

    private static final long serialVersionUID = 1521974978161487658L;

    public NonExistentAlgorithmException() {
        super();
    }

    public NonExistentAlgorithmException(String message) {
        super(message);
    }

    public NonExistentAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentAlgorithmException(Throwable cause) {
        super(cause);
    }

    protected NonExistentAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

