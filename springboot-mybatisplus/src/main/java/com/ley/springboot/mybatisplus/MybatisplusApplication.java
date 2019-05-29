package com.ley.springboot.mybatisplus;

import com.ley.springboot.mybatisplus.configuration.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {MybatisPlusConfig.MAPPER_SCAN_PACKAGE})
public class MybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusApplication.class, args);
    }
}
