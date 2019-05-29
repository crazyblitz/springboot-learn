package com.gitee.ley.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.gitee.ley.mybatis.utils.DBTypeEnum;
import com.gitee.ley.mybatis.utils.DbContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid数据源配置
 *
 * @author liuenyuan
 **/
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    /**
     * datasource 1
     **/
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * datasource 2
     **/
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(@Qualifier("db1") DataSource db1,
                                        @Qualifier("db2") DataSource db2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DBTypeEnum.DB_1.getValue(), db1);
        targetDataSources.put(DBTypeEnum.DB_2.getValue(), db2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源是db1
        dynamicDataSource.setDefaultTargetDataSource(db1);
        return dynamicDataSource;
    }

    /**
     * dynamic data source(动态数据源)
     **/
    private static class DynamicDataSource extends AbstractRoutingDataSource {

        /**
         * 决定数据源的路由
         **/
        @Override
        protected Object determineCurrentLookupKey() {
            return DbContextHolder.getDbType();
        }
    }
}
