package com.ley.springboot.aop.introduction.service.impl;

import com.ley.springboot.aop.introduction.service.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnimalImpl implements Animal {
    @Override
    public void eat() {
        log.info("animal eat");
    }
}
