package com.ley.springboot.aop.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ley.springboot.aop.life")
public class UserConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setAge(19);
        user.setName("刘恩源");
        return user;
    }

    @Bean
    public UserBeanProcessor userBeanProcessor() {
        return new UserBeanProcessor();
    }


}
