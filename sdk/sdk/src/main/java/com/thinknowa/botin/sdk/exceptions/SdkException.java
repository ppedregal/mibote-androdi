package com.thinknowa.botin.sdk.exceptions;

/**
 * Created by ppedregal on 17/11/15.
 */
public class SdkException extends RuntimeException {

    public SdkException() {
    }

    public SdkException(String message) {
        super(message);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SdkException(Throwable cause) {
        super(cause);
    }
}
