package com.crazyblitz.spring.boot.config.annotation;


import java.lang.annotation.*;

/**
 * @author liuenyuan
 * @see YamlPropertySource
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YamlPropertySources {

    YamlPropertySource[] value();
}
