package com.gitee.ley.mybatis.aspect;

import com.gitee.ley.mybatis.utils.DBTypeEnum;
import com.gitee.ley.mybatis.utils.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源切换切面<br/>
 * 尽量使order小,为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
 *
 * @author liuenyuan
 **/
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
@Slf4j
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.gitee.ley.mybatis.service.impl1.*.*(..))")
    private void db1Pointcut() {
    }

    @Pointcut("execution(* com.gitee.ley.mybatis.service.impl2.*.*(..))")
    private void db2Pointcut() {
    }

    /**
     * 执行之前,切换数据源
     **/
    @Before("db1Pointcut()")
    public void beforeDb1() {
        log.info("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.DB_1);
    }

    @Before("db2Pointcut()")
    public void beforeDb2() {
        log.info("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.DB_2);
    }

    /**
     * 执行完切面后,将线程共享中的数据源名称清空
     **/
    @After("db1Pointcut()")
    public void afterDb1() {
        log.info("清空线程共享中的数据源名称: {}", DBTypeEnum.DB_1);
        DbContextHolder.clearDbType();
    }

    @After("db2Pointcut()")
    public void afterDb2() {
        log.info("清空线程共享中的数据源名称: {}", DBTypeEnum.DB_2);
        DbContextHolder.clearDbType();
    }
}