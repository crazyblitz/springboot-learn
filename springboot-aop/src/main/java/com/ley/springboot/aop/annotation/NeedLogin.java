package com.ley.springboot.aop.annotation;

import java.lang.annotation.*;

/**
 * 验证访问的URL是否需要登录
 *
 * @author liuenyuan
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NeedLogin {

    boolean value() default true;
}
