package com.ley.spring.customized.annotation.enable.module;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:19
 * @describe server type
 */
public interface Server {

    /**
     * start server
     **/
    void start();


    /**
     * 关闭服务器
     **/
    void stop();


    enum Type {

        /**
         * http Server
         **/
        HTTP,

        /**
         * ftp server
         **/
        FTP;
    }

}
