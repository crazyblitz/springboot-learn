package com.ley.crazyblitz;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
