package com.ley.springboot.aop.code;

import com.ley.springboot.commons.aop.AopUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.ley.springboot.aop.code.service..*.*(..))")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = AopUtils.getMethodFromTarget(joinPoint);
        Object[] args = joinPoint.getArgs();
        log.info("method: {},args: {}", method, args);
        return joinPoint.proceed();
    }
}
