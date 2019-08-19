package com.ley.spring.learn.nest.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class NestAopApplication {

    @Autowired
    private PersonService personService;

    @PostConstruct
    protected void nestAop(){
        personService.nestAop();
        personService.nestAopHandle();
        personService.aopInThread();
    }

    public static void main(String[] args) {
        SpringApplication.run(NestAopApplication.class,args);
    }
}
