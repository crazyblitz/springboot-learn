package com.ley.spring.learn.bean.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryProcessorMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfig.class);
        User user = (User) ctx.getBean("user");
        System.out.println(user);
        ctx.close();
    }
}
