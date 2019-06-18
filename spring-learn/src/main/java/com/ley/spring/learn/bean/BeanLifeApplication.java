package com.ley.spring.learn.bean;


import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter
 * @see org.springframework.beans.factory.InitializingBean
 * @see org.springframework.beans.factory.DisposableBean
 * @see org.springframework.beans.factory.BeanNameAware
 * @see org.springframework.beans.factory.BeanFactoryAware
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
 **/
public class BeanLifeApplication {

    public static void main(String[] args) {
        System.out.println("--------------【初始化容器】---------------");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.ley.spring.learn.bean");
        //得到studentBean，并显示其信息
        Student studentBean = context.getBean(Student.class);
        System.out.println(studentBean);

        System.out.println("--------------------【销毁容器】----------------------");
        context.registerShutdownHook();
    }
}
