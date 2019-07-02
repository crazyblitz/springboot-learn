package com.gitee.ley.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuenyuan
 **/
@SpringBootApplication(scanBasePackages = "com.gitee.ley.mybatis")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
