package com.ley.spring.learn.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationContextHolder implements ApplicationContextAware, BeanFactoryAware {

    private static ApplicationContext applicationContext;

    private static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "applicationContext is not null");
        ApplicationContextHolder.applicationContext = applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public static String[] getBeanDefinitionNames() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ApplicationContextHolder.beanFactory;
        return beanFactory.getBeanDefinitionNames();
    }


    public static BeanDefinition getBeanDefinition(String beanName) {
        return ((DefaultListableBeanFactory) beanFactory).getBeanDefinition(beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        Assert.notNull(beanFactory, "BeanFactory is not null");
        ApplicationContextHolder.beanFactory = beanFactory;
    }


}
