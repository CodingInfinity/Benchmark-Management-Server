package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.exception;

/**
 * Created by reinhardt on 2016/06/26.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown when a user tries to register an username that already exists in the system.
 *
 * @author Reinhardt Cromhout
 *
 * @since 1.0.0
 * @version 1.0.0
 */


@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason = "Duplicate Algorithm Name")
public class DuplicateAlgorithmException extends Exception{
    public DuplicateAlgorithmException() {
        super();
    }

    public DuplicateAlgorithmException(String message) {
        super(message);
    }

    public DuplicateAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAlgorithmException(Throwable cause) {
        super(cause);
    }

    public DuplicateAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
