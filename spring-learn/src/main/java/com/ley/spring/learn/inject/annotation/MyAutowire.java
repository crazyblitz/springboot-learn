package com.ley.spring.learn.inject.annotation;

import java.lang.annotation.*;

/**
 * @author liuenyuan
 * @see org.springframework.beans.factory.annotation.Autowire
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowire {
}
