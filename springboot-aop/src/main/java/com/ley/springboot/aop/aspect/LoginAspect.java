package com.ley.springboot.aop.aspect;

import com.google.gson.Gson;
import com.ley.springboot.aop.annotation.NeedLogin;
import com.ley.springboot.commons.aop.AopUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 用于登录拦截的切面,如果用户没有登录,不能访问(AOP只能拦截到方法级)
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class LoginAspect {

    @Autowired
    private Gson gson;


    private final static String SESSION_USER_NAME = "userName";

    /**
     * 扫描所以带有特定注解的类方法
     */
    @Pointcut(value = "@annotation(com.ley.springboot.aop.annotation.NeedLogin) && execution(* com.ley.springboot.aop.controller.*.*(..))")
    private void loginPointcut() {
    }


    @Around(value = "loginPointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = AopUtils.getMethodFromTarget(joinPoint);
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        boolean value = needLogin.value();
        if (!value && session.getAttribute(SESSION_USER_NAME) != null) {
            return joinPoint.proceed();
        } else {
            return gson.toJson("您还没登录");
        }
    }


    private HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return request;
    }

}
