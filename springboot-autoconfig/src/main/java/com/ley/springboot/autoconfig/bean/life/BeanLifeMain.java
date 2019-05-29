package com.ley.springboot.autoconfig.bean.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanLifeMain {

    public static void main(String[] args) {
        SpringApplication.run(BeanLifeMain.class, args);
        System.exit(1);
    }
}
