package com.ley.spring.learn.inject;

import com.ley.spring.learn.inject.factory.annotation.EnableMyInject;
import com.ley.spring.learn.inject.factory.annotation.MyInjectAnnotationBeanProcessor;
import com.ley.spring.learn.inject.factory.annotation.MyInjectAnnotationBeanProcessorRegistrar;
import com.ley.spring.learn.inject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@Slf4j
@EnableMyInject
@ComponentScan(basePackages = "com.ley.spring.learn.inject")
public class InjectMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectMain.class);
        System.out.println(context.getBean(MyInjectAnnotationBeanProcessor.class));
        System.out.println(context.getBean(MyInjectAnnotationBeanProcessorRegistrar.class));
        System.out.println(context.getBean("user"));
        System.out.println(context.getBean(UserService.class).getUser1());
        Arrays.asList(context.getBeanDefinitionNames()).stream()
                .forEach(System.out::println);
        context.close();
    }


}
