package com.ley.spring.customized.annotation.repository;

import java.lang.annotation.*;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/26 9:01
 * @describe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@FirstLevelRepository
public @interface SecondLevelRepository {
}
