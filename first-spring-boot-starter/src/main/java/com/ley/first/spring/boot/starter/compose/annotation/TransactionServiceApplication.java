package com.ley.first.spring.boot.starter.compose.annotation;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.Map;

/**
 * 类描述: 测试元注解属性隐性覆盖
 *
 * @author liuenyuan
 * @date 2019/4/27 11:03
 * @describe
 * @see org.springframework.core.annotation.AliasFor
 */
@Configuration
@ComponentScan(basePackageClasses = {TransactionServiceBean.class})
@EnableTransactionManagement
public class TransactionServiceApplication {

    @Test
    public void testAnnotationAttributeImplicitOverride() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(TransactionServiceApplication.class);
        Map<String, TransactionServiceBean> beansMap = ctx.getBeansOfType(TransactionServiceBean.class);

        beansMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称 : %s,对象 : %s \n",
                    beanName, bean);
            bean.save();
        });
        ctx.close();
    }


    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager: 事物提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
                System.out.println("txManager: 事物回滚...");
            }
        };
    }


    @Bean("txManager2")
    public PlatformTransactionManager txManager2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager2: 事物提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
                System.out.println("txManager2: 事物回滚...");
            }
        };
    }
}
