package com.ley.crazyblitz.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private int code = 0;
    private String status = "0";
    private String message;
    private T data;

    public ApiResult() {
    }

    public int getCode() {
        return this.code;
    }

    public ApiResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ApiResult<T> success() {
        this.code = 0;
        return this;
    }

    public ApiResult<T> success(T data) {
        this.code = 0;
        this.data = data;
        return this;
    }

    public ApiResult<T> fail() {
        this.code = -1;
        return this;
    }

    public ApiResult<T> fail(String message) {
        this.code = -1;
        this.message = message;
        return this;
    }

    public ApiResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        if (this.getCode() != 0) {
            this.setStatus("-1");
        }

        return this.status;
    }

    public ApiResult<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
