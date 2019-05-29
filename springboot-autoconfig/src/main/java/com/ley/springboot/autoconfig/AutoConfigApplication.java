package com.ley.springboot.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutoConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AutoConfigApplication.class, args);
        AutoConfigurationPackages.get(ctx.getBeanFactory()).forEach(System.out::println);
        ctx.close();
    }
}
