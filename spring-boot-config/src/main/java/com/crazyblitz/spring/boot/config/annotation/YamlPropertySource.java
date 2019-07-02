package com.crazyblitz.spring.boot.config.annotation;


import com.crazyblitz.spring.boot.config.annotation.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.lang.annotation.*;

/**
 * yaml property source and extension {@link PropertySource}
 *
 * @author liuenyuan
 * @see org.springframework.context.annotation.PropertySource
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(YamlPropertySources.class)
public @interface YamlPropertySource {


    /**
     * @see PropertySource#name()
     */
    String name() default "";

    /**
     * @see PropertySource#value()
     */
    String[] value();

    /**
     * @see PropertySource#ignoreResourceNotFound()
     * @since 4.0
     */
    boolean ignoreResourceNotFound() default false;

    /**
     * @see PropertySource#encoding()
     * @see org.springframework.core.io.support.EncodedResource
     * @since 4.3
     */
    String encoding() default "";

    /**
     * @see PropertySource#factory()
     * @see org.springframework.core.io.support.DefaultPropertySourceFactory
     * @see org.springframework.core.io.support.ResourcePropertySource
     * @since 4.3
     */
    Class<? extends PropertySourceFactory> factory() default YamlPropertySourceFactory.class;
}
