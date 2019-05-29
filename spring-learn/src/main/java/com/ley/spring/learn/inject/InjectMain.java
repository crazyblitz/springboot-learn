package com.ley.spring.learn.inject;

import com.ley.spring.learn.inject.factory.annotation.EnableMyInject;
import com.ley.spring.learn.inject.factory.annotation.MyInjectAnnotationBeanProcessor;
import com.ley.spring.learn.inject.factory.annotation.MyInjectAnnotationBeanProcessorRegistrar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@SpringBootApplication
@EnableMyInject
@ComponentScan(basePackages = "com.ley.spring.learn.inject")
public class InjectMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InjectMain.class, args);
        System.out.println(context.getBean(MyInjectAnnotationBeanProcessor.class));
        System.out.println(context.getBean(MyInjectAnnotationBeanProcessorRegistrar.class));
        System.out.println(context.getBean("user"));
    }


}
