package com.ley.spring.customized.annotation.derivative.annotation;

import com.ley.spring.customized.annotation.derivative.repository.Repository;

import java.lang.annotation.*;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/26 9:00
 * @describe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repository
public @interface FirstLevelRepository {
}
