package com.ley.crazyblitz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot整合jwt
 *
 * @author zhiyuan
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.ley.crazyblitz.business.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
