package com.ley.springboot.autoconfig.annotation.config;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;


public class AnnotationConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        List<String> beanNames = Arrays.asList(beanFactory.getBeanDefinitionNames());
        beanNames.stream().forEach(beanName -> {
            System.out.println(beanName);
        });
        context.close();
    }
}
