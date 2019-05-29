package com.ley.springboot.aop.life;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class UserBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Bean前置处理: {},bean: {},beanName: {}", "postProcessBeforeInitialization()", bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("Bean后置处理: {},bean: {},beanName: {}", "postProcessAfterInitialization()", bean, beanName);
        return bean;
    }

}
