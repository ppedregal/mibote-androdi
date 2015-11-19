package com.thinknowa.botin.sdk.exceptions;

/**
 * Created by ppedregal on 18/11/15.
 */
public class ResponseException extends SdkException {

    private final int code;
    private final String message;
    private final String body;

    public ResponseException(int code,String message,String body) {
        this.code=code;
        this.message=message;
        this.body=body;
    }

    public ResponseException(int code,String message){
        this(code,message,null);
    }

    public ResponseException(int code){
        this(code,null,null);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ResponseException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
