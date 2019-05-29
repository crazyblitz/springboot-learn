package com.ley.springboot.aop.life;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @see AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
 * @see AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
 * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
 * @see org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor
 **/
@Data
@Slf4j
public class User implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, InitializingBean, DisposableBean {

    private Integer age;

    private String name;

    private String beanName;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    @PostConstruct
    protected void customInitByAnnotation() {
        log.info("class: {},bean custom init method by annotation: {}", this.getClass(), "customInitByAnnotation()");
        this.age = 21;
        this.name = "customInitByAnnotation()";
    }

    @PreDestroy
    protected void customDestroyByAnnotation() {
        log.info("class: {},bean custom destroy method by annotation: {}", this.getClass(), "customDestroyByAnnotation()");
        name = "customDestroyByAnnotation";
        age = null;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        log.info("class: {},classLoader: {}", this.getClass(), this.classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.info("class: {},beanFactory: {}", this.getClass(), this.beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        log.info("class: {},beanName: {}", this.getClass(), this.beanName);
    }

    @Override
    public void destroy() throws Exception {
        log.info("class: {},bean destroy method: {}", this.getClass(), "destroy()");
        name = "destroy";
        age = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("class: {},bean init method: {}", this.getClass(), "afterPropertiesSet()");
        this.age = 20;
        this.name = "afterPropertiesSet()";
    }
}
