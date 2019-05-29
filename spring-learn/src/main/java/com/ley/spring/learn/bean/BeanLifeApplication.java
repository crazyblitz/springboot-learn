package com.ley.spring.learn.bean;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
