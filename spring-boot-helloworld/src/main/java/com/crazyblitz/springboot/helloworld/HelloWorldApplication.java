package com.crazyblitz.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述: hello world spring boot application
 *
 * @author liuenyuan
 * @date 2019/6/15 10:03
 * @describe
 */
@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }


    @RestController
    public class HelloController {

        @GetMapping("/hello")
        public String hello() {
            return "my first spring boot application.";
        }
    }
}
