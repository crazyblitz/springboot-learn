package com.ley.first.spring.boot.starter.compose.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 类描述:组合注解
 *
 * @author liuenyuan
 * @date 2019/4/25 20:11
 * @describe
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Transactional
@Service(value = "transactionalService")
public @interface TransactionalService {

    /**
     * @return 服务Bean名称
     **/
    @AliasFor("value")
    String name() default "txManager";


    /**
     * 覆盖{@link Transactional#transactionManager()}默认值
     *
     * @return {@link org.springframework.transaction.PlatformTransactionManager}Bean名称,默认关联
     * <br/>
     * "txManager"Bean
     **/
    /**
     * String transactionManager() default "txManager";
     **/


    @AliasFor("name")
    String value() default "txManager";


    /**
     * 建立{@link Transactional#transactionManager()}别名
     *
     * @return {@link org.springframework.transaction.PlatformTransactionManager}Bean名称,默认关联
     * "txManager"Bean.
     **/
    @AliasFor(annotation = Transactional.class, attribute = "transactionManager")
    String manager() default "txManager";
}
