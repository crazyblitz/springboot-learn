package com.ley.first.spring.boot.starter.spring.factories;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 类描述: enable configuration
 *
 * @author liuenyuan
 * @date 2019/5/29 23:11
 * @describe
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ConfigurationImportSelector.class)
@Configuration
public @interface EnableConfiguration {
}
