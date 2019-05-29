package com.ley.spring.learn.inject.factory.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyInjectAnnotationBeanProcessorRegistrar.class)
public @interface EnableMyInject {
}
