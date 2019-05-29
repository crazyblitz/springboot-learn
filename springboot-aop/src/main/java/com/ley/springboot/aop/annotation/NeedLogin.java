package com.ley.springboot.aop.annotation;

import java.lang.annotation.*;

/**
 * 验证访问的URL是否需要登录
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NeedLogin {

    boolean value() default true;
}
