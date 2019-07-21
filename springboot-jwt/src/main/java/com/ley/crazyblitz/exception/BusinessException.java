package com.ley.crazyblitz.exception;

import com.ley.crazyblitz.constants.NetConstants;

public class BusinessException extends RuntimeException {
    protected int code= NetConstants.RTN_SYS_ERROR;

    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}