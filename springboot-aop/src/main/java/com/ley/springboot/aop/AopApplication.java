package com.ley.springboot.aop;

import com.ley.springboot.aop.aspect.NeedLoginAttributeSourceAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ley.springboot.aop.controller",
        "com.ley.springboot.aop.aspect", "com.ley.springboot.aop.service"})
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
        NeedLoginAttributeSourceAdvisor.getMatchMethods().forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
    }
}
