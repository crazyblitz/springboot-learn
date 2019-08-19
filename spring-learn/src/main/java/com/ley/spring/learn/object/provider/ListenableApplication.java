package com.ley.spring.learn.object.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "com.ley.spring.learn.object.provider")
public class ListenableApplication {

    @Resource
    private UserService userService;

    @PostConstruct
    public void hello() {
        userService.objectProvider();
        System.out.println(userService.getThreadPool());
        userService.objectProvider2();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext  context=SpringApplication.run(ListenableApplication.class, args);
        context.close();
    }
}
