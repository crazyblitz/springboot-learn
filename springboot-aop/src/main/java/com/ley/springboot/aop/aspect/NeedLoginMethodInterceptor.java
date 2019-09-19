package com.ley.springboot.aop.aspect;

import com.google.gson.Gson;
import com.ley.springboot.aop.annotation.NeedLogin;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class NeedLoginMethodInterceptor implements MethodInterceptor {

    private final static String SESSION_USER_NAME = "userName";

    private Gson gson;

    public NeedLoginMethodInterceptor(Gson gson) {
        this.gson = gson;
        new RequestContextListener();
    }

    /**
     * @see org.springframework.aop.framework.ReflectiveMethodInvocation
     * @see RequestContextListener
     * @see RequestContextHolder
     * @see org.springframework.web.filter.RequestContextFilter
     * @see org.springframework.web.servlet.support.RequestContextUtils
     **/
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        NeedLogin methodNeedLogin = AnnotationUtils.getAnnotation(invocation.getMethod(), NeedLogin.class);
        NeedLogin classNeedLogin = AnnotationUtils.getAnnotation(AopUtils.getTargetClass(invocation.getThis()),
                NeedLogin.class);
        if (log.isInfoEnabled()) {
            log.info("invocation: {}", invocation);
            log.info("classNeedLogin: {},methodNeedLogin: {}", classNeedLogin, methodNeedLogin);
        }
        Object proceed = gson.toJson("您还没登录");
        if (classNeedLogin != null || methodNeedLogin != null) {
            HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            boolean findSession = StringUtils.hasText((String) request.getSession().getAttribute(SESSION_USER_NAME));
            boolean methodProceed;
            if (classNeedLogin != null) {
                methodProceed = !classNeedLogin.value();
            } else if (methodNeedLogin != null) {
                methodProceed = !methodNeedLogin.value();
            } else {
                methodProceed = (!classNeedLogin.value() && !methodNeedLogin.value());
            }
            boolean canProceed = findSession || methodProceed;
            if (canProceed) {
                proceed = invocation.proceed();
            }
        }
        return proceed;
    }
}
