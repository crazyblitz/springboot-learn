package com.ley.springboot.commons.reflect;

public enum MethodName {

    /**
     * set method
     **/
    SET("set"),

    /**
     * get method
     **/
    GET("get");

    private String methodName;

    MethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
