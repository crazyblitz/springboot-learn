package com.ley.springboot.commons.web.result;

/**
 * response result
 **/
public class Result {


    /**
     * response result is success with response code
     **/
    public static ResponseMessage success() {
        return new ResponseMessage(ResponseMessageCode.SUCCESS.getCode(), "", null, true);
    }


    /**
     * response result is success with response code and data
     **/
    public static <T> ResponseMessage<T> success(String code, T t) {
        return new ResponseMessage(code, "", t, true);
    }


    /**
     * response result is success with all
     **/
    public static <T> ResponseMessage<T> success(String code, String message, T t) {
        return new ResponseMessage(code, message, t, true);
    }

    /**
     * response result is success with data
     **/
    public static <T> ResponseMessage<T> success(T t) {
        return new ResponseMessage(ResponseMessageCode.SUCCESS.getCode(), "", t, true);
    }


    /**
     * response result is handler without all
     **/
    public static ResponseMessage error() {
        return error("");
    }


    /**
     * response result is handler with message
     **/
    public static ResponseMessage error(String message) {
        return error(ResponseMessageCode.ERROR.getCode(), message);
    }


    /**
     * response result is handler with code and message
     **/
    public static ResponseMessage error(String code, String message) {
        return error(code, message, null);
    }


    /**
     * response result is handler with code and message and data
     **/
    public static <T> ResponseMessage<T> error(String code, String message, T t) {
        return new ResponseMessage(code, message, t, false);
    }
}