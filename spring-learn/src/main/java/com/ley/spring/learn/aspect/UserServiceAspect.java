package com.ley.spring.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserServiceAspect {

    @Pointcut("execution(* com.ley.spring.learn.aspect.UserService.*(..))")
    protected void pointcut() {

    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println(String.format("method: %s,args: %s,target: %s", joinPoint.getSignature(),
                joinPoint.getArgs(), joinPoint.getTarget()));
    }
}
