package com.thinknowa.botin.sdk.exceptions;

/**
 * Created by ppedregal on 18/11/15.
 */
public class NetworkException extends SdkException {

    public NetworkException() {
    }

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }
}
