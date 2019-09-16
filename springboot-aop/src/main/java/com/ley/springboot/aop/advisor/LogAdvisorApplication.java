package com.ley.springboot.aop.advisor;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LogAdvisorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LogAdvisorApplication.class, args);
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.hello();
        System.out.println(context.getBeansOfType(AbstractAutoProxyCreator.class));
        context.close();
    }
}
