package com.ley.spring.learn.bean.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class UserConfig {

    @Bean
    public UserBeanFactoryProcessor userBeanFactoryProcessor() {
        return new UserBeanFactoryProcessor();
    }

    @Bean("user")
    public User user() {
        User user = new User();
        user.setAge("23");
        user.setName("刘恩源23");
        return user;
    }
}
