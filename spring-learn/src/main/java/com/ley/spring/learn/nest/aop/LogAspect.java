package com.ley.spring.learn.nest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.ley.spring.learn.nest.aop.*.*(..))")
    protected void poincut() {

    }

    @Before("poincut()")
    public void before(JoinPoint joinPoint) {
        log.info("enter method: {}", joinPoint.getSignature().getName());
    }
}
