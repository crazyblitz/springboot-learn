package com.ley.springboot.aop.advisor;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class LogPrinterAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public LogPrinterAdvisor() {
        setAdvice(new LogPrinterMethodInterceptor());
    }

    @Override
    public boolean matches(@NonNull Method method, Class<?> targetClass) {
        Method m = method;
        if (isAnnotationPresent(m)) {
            return true;
        }

        if (targetClass != null) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return isAnnotationPresent(m);
            } catch (NoSuchMethodException ignored) {

            }
        }
        return false;
    }

    private boolean isAnnotationPresent(Method method) {
        Annotation a = AnnotationUtils.findAnnotation(method, LogPrinter.class);
        return a != null;
    }
}
