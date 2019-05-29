package com.ley.first.spring.boot.starter.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/27 20:11
 * @describe
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnClassCondition.class)
public @interface ConditionalOnClass {

    /**
     * The classes names that must be present.
     *
     * @return the class names that must be present.
     */
    String[] name() default {};

    /**
     * search classes base packages
     **/
    String[] basePackages() default {};
}
