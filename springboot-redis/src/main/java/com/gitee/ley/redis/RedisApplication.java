package com.gitee.ley.redis;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RedisApplication.class);
        builder.bannerMode(Banner.Mode.CONSOLE).web(WebApplicationType.NONE)
                .run(args);
    }
}
