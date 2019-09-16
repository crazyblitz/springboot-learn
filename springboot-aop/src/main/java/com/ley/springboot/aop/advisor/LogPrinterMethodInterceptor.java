package com.ley.springboot.aop.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.ClassUtils;

@Slf4j
public class LogPrinterMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LogPrinter logPrinter = invocation.getMethod().getAnnotation(LogPrinter.class);
        System.out.println(ClassUtils.getQualifiedName(LogPrinter.class) + ":" + logPrinter.value());
        return invocation.proceed();
    }
}
