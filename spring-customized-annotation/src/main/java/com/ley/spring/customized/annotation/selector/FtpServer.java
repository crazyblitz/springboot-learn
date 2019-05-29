package com.ley.spring.customized.annotation.selector;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:21
 * @describe
 */
@Slf4j
public class FtpServer implements Server {
    @Override
    public void start() {
        log.info("ftp server start...");
    }

    @Override
    public void stop() {
        log.info("ftp server stop...");
    }
}
