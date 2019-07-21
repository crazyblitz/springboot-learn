package com.ley.crazyblitz.constants;

public interface NetConstants {
    /**
     * 接口调用成功
     */
    int RTN_SUCCESS = 0;
    /**
     * 用户校验失败
     */
    int RTN_UNAUTH = 100;
    /**
     * token过期
     */
    int RTN_TOKEN_EXPIRE = 50;
    /**
     * 接口调用失败
     */
    int RTN_FAILED = -1;
    /**
     * 查询结果过多
     */
    int RTN_TOO_MANY = -5;
    /**
     * 请求过于频繁
     */
    int RTN_REQ_FREQUENT = -6;
    /**
     * 系统错误
     */
    int RTN_SYS_ERROR = 500;
    /**
     * 参数错误
     */
    int RTN_PARAMS_ERROR = 600;
}