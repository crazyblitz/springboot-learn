package com.ley.spring.learn.bean.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class User {
    private String name;
    private String age;

    protected void init() {
        name = "刘恩源";
        age = "21";
        log.info("custom init user");
    }

    protected void destroy() {
        log.info("destroy user");
    }
}
