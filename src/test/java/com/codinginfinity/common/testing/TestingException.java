package com.codinginfinity.common.testing;

/**
 * Created by andrew on 2016/07/06.
 */
public class TestingException extends Exception{

    private static final long serialVersionUID = -7489350570171376594L;

    public TestingException() {
        super();
    }

    public TestingException(String message) {
        super(message);
    }

    public TestingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestingException(Throwable cause) {
        super(cause);
    }

    protected TestingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
