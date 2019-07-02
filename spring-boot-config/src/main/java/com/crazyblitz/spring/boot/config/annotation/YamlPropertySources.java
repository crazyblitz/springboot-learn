package com.crazyblitz.spring.boot.config.annotation;


import org.springframework.context.annotation.PropertySources;

import java.lang.annotation.*;

/**
 * @author liuenyuan
 * @see YamlPropertySource
 * @see org.springframework.context.annotation.PropertySources
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YamlPropertySources {

    /**
     * @see PropertySources#value()
     **/
    YamlPropertySource[] value();
}
