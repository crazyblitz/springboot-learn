package com.ley.spring.learn.bean.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * @see org.springframework.beans.factory.config.BeanPostProcessor
 * @see org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor
 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor
 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter
 **/
public class BeanFactoryProcessorMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class);
        User user = (User) ctx.getBean("user");
        System.out.println(user);
        ctx.close();
    }
}
