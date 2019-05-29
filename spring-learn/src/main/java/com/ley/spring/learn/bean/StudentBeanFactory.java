package com.ley.spring.learn.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class StudentBeanFactory implements BeanFactoryPostProcessor {

    public StudentBeanFactory() {
        System.out.println("【BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor实现类构造方法");
    }

    /**
     * 重写BeanFactoryPostProcessor接口的postProcessBeanFactory方法，可通过该方法对beanFactory进行设置
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("【BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("student");
        beanDefinition.getPropertyValues().addPropertyValue("age", "21");
    }
}