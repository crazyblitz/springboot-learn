package com.ley.spring.learn.utils;

import com.ley.spring.learn.aspect.AspectConfig;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author liuenyuan
 * @see org.springframework.core.MethodIntrospector
 **/
public class MethodIntrospectorTest {

    @Test
    public void testMethodIntrospector() {
        Map<Method, Bean> beanMethods = MethodIntrospector.selectMethods(AspectConfig.class,
                (MethodIntrospector.MetadataLookup<Bean>) method -> AnnotationUtils.findAnnotation(method,
                        Bean.class));
        Set<Method> methods = beanMethods.keySet();
        Stream.of(methods).forEach(System.out::println);
    }
}
