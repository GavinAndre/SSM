package com.java.gavinandre.exception;

/**
 * Created by gavinandre on 17-1-10.
 */
public class CustomException extends Exception {

    //异常信息
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
