package com.ley.springboot.aop.life;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class LifecycleMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
        User user = context.getBean(User.class);
        log.info("user: {}", user);
        context.close();
    }
}
