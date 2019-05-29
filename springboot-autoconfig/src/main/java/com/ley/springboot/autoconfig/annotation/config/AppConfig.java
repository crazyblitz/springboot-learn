package com.ley.springboot.autoconfig.annotation.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class AppConfig {

    @Data
    class User {
        private String name;

        private Integer age;
    }


    @Bean
    public User user() {
        return new User();
    }
}
