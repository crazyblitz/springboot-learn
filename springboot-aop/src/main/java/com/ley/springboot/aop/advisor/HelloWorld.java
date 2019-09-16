package com.ley.springboot.aop.advisor;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    @LogPrinter("hello world")
    public void hello() {
        System.out.println("Hello");
    }
}
