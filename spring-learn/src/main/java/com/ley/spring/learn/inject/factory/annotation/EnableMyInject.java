package com.ley.spring.learn.inject.factory.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liuenyuan
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyInjectAnnotationBeanProcessorRegistrar.class)
public @interface EnableMyInject {
}
