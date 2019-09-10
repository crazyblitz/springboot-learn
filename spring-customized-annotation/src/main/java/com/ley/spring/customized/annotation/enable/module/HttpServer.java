package com.ley.spring.customized.annotation.enable.module;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:20
 * @describe
 */
@Slf4j
public class HttpServer implements Server {
    @Override
    public void start() {
        log.info("http server start...");
    }

    @Override
    public void stop() {
        log.info("http server stop...");
    }
}
