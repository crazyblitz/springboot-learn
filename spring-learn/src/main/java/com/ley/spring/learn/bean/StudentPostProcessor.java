package com.ley.spring.learn.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessBeforeInitialization方法，这里可对"+beanName+"的属性进行更改。");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessAfterInitialization方法，这里可对"+beanName+"的属性进行更改。");
        return bean;
    }
}
