package com.ley.spring.learn.inject.config;


import com.ley.spring.learn.inject.annotation.MyAutowire;
import com.ley.spring.learn.inject.user.User;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setName("刘恩源");
        user.setAge(23);
        return user;
    }


    /**
     * @see AutowiredAnnotationBeanPostProcessor#setAutowiredAnnotationType(Class)
     **/
    @Bean
    public AutowiredAnnotationBeanPostProcessor myAutowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setAutowiredAnnotationType(MyAutowire.class);
        return postProcessor;
    }


}
