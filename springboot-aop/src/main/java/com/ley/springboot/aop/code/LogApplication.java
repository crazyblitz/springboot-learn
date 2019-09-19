package com.ley.springboot.aop.code;

import com.ley.springboot.aop.code.config.LogAspectConfig;
import com.ley.springboot.aop.code.service.CodeService;
import com.ley.springboot.aop.code.service.impl.CodeServiceImpl;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * log application
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class LogApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LogAspectConfig.class);
        CodeService codeService = ctx.getBean(CodeService.class);
        codeService.add(1, 2);
        System.out.println(ctx.getBean(LogAspectConfig.class));
        System.out.println(AopUtils.isJdkDynamicProxy(codeService));
        System.out.println(AopUtils.isAopProxy(codeService));
        System.out.println(AopUtils.getTargetClass(codeService));
        System.out.println(ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(
                AopUtils.getTargetClass(codeService), "add", int.class, int.class
        ), codeService, 1, 3));
        ReflectionUtils.doWithMethods(AopUtils.getTargetClass(codeService),
                mc -> {
                    try {
                        mc.invoke(codeService, 1, 4);
                    } catch (InvocationTargetException e) {
                        ReflectionUtils.handleReflectionException(e);
                    }
                }, mf -> mf.getName().equals("add"));
        ctx.close();
    }

}
