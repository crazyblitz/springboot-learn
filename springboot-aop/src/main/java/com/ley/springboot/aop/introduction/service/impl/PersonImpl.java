package com.ley.springboot.aop.introduction.service.impl;

import com.ley.springboot.aop.introduction.service.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonImpl implements Person {

    @Override
    public void likePerson() {
        log.info("like person");
    }
}
