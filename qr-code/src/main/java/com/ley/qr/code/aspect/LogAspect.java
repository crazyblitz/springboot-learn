package com.ley.qr.code.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Aliue
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private static ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(* com.ley.qr.code.controller.*.*(..))")
    private void logPointcut() {
    }

    @Before("logPointcut()")
    public void before(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("methodName: {},params: {}", joinPoint.getSignature(), CollectionUtils.arrayToList(joinPoint.getArgs()));
        }
        timeThreadLocal.set(System.nanoTime());
    }

    @After("logPointcut()")
    public void after(JoinPoint joinPoint) {
        long startTime = timeThreadLocal.get();
        long endTime = System.nanoTime();
        if (log.isInfoEnabled()) {
            log.info("methodName: {} cost time: {} ms", joinPoint.getSignature(),
                    TimeUnit.NANOSECONDS.toMillis((endTime - startTime)));
        }
    }
}
