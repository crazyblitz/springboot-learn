package com.ley.springboot.aop.aspect;

import com.google.gson.Gson;
import com.ley.springboot.aop.annotation.NeedLogin;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * 用于登录拦截的切面,如果用户没有登录,不能访问(AOP只能拦截到方法级)
 *
 * @author liuenyuan
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
    @Pointcut(value = "(@annotation(com.ley.springboot.aop.annotation.NeedLogin) && execution(* com.ley.springboot.aop.controller.*.*(..)))" +
            " || @within(com.ley.springboot.aop.annotation.NeedLogin)")
    private void loginPointcut() {
    }


    @Around(value = "loginPointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取类上注解
        System.out.println(joinPoint);
        Class<?> targetClass = AopUtils.getTargetClass(joinPoint.getTarget());
        if (AnnotationUtils.findAnnotation(targetClass, NeedLogin.class) != null) {
            NeedLogin needLogin = AnnotationUtils.getAnnotation(targetClass, NeedLogin.class);
            if (!needLogin.value()) {
                return joinPoint.proceed();
            } else {
                return gson.toJson("您还没登录");
            }
        } else {
            Method method = com.ley.springboot.commons.aop.AopUtils.getMethodFromTarget(joinPoint);
            NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
            HttpServletRequest request = getRequest();
            boolean findSession = StringUtils.hasText((String) request.getSession().getAttribute(SESSION_USER_NAME));
            Enumeration<String> attributeNames = request.getSession().getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                System.out.println(attributeNames.nextElement());
            }
            System.out.println(request.getSession().getAttribute(SESSION_USER_NAME));
            boolean value = needLogin.value();
            if ((!value) || (findSession && value)) {
                return joinPoint.proceed();
            } else {
                return gson.toJson("您还没登录");
            }
        }
    }


    private HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return request;
    }

}
