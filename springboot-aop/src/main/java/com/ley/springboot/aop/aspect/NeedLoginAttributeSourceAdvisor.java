package com.ley.springboot.aop.aspect;

import com.google.gson.Gson;
import com.ley.springboot.aop.annotation.NeedLogin;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class NeedLoginAttributeSourceAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private final Class<NeedLogin> annotationClass = NeedLogin.class;

    private static final ConcurrentHashMap<String, Method> matchMethods = new ConcurrentHashMap<>(256);


    public NeedLoginAttributeSourceAdvisor(Gson gson) {
        setAdvice(new NeedLoginMethodInterceptor(gson));
    }

    @Override
    public boolean matches(@NonNull Method method, Class targetClass) {

        Method m = method;

        if (isNeedLoginAnnotationPresent(m)) {
            matchMethods.put(ClassUtils.getQualifiedName(targetClass) + "." + m.getName(), m);
            return true;
        }

        //The 'method' parameter could be from an interface that doesn't have the annotation.
        //Check to see if the implementation has it.
        if (targetClass != null) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                boolean match = isNeedLoginAnnotationPresent(m) || isNeedLoginAnnotationPresent(targetClass);
                if (match) {
                    matchMethods.put(ClassUtils.getQualifiedName(targetClass) + "." + m.getName(), m);
                }
                return match;
            } catch (NoSuchMethodException ignored) {
                //default return value is false.  If we can't find the method, then obviously
                //there is no annotation, so just use the default return value.
            }
        }

        return false;
    }

    private boolean isNeedLoginAnnotationPresent(Class<?> targetClazz) {
        Annotation a = AnnotationUtils.findAnnotation(targetClazz, annotationClass);
        return a != null;
    }

    private boolean isNeedLoginAnnotationPresent(Method method) {
        Annotation a = AnnotationUtils.findAnnotation(method, annotationClass);
        return a != null;
    }

    public static ConcurrentHashMap<String, Method> getMatchMethods() {
        return matchMethods;
    }
}
