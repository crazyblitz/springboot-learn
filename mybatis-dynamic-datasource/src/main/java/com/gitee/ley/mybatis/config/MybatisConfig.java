package com.gitee.ley.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import javax.sql.DataSource;
/**
 * mybatis configuration(建议使用配置类的方式重新声明相关bean)
 *
 * @author liuenyuan
 **/
@Configuration
@MapperScan(basePackages = {"com.gitee.ley.mybatis.dao"})
@AutoConfigureAfter(value = {DruidDataSourceConfig.class, DataSourceAutoConfiguration.class})
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean(DataSource.class)
@Slf4j
public class MybatisConfig {


    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.config-location}")
    private String configLocation;


    private final Interceptor[] interceptors;

    public MybatisConfig(ObjectProvider<Interceptor[]> interceptors) {
        this.interceptors = interceptors.getIfAvailable();
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DruidDataSourceConfig druidDataSourceConfig) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(druidDataSourceConfig.dynamicDataSource(druidDataSourceConfig.db1(), druidDataSourceConfig.db2()));
        ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(null);
        Resource[] mapperResources = resourcePatternResolver.getResources(mapperLocations);
        sqlSessionFactory.setMapperLocations(mapperResources);
        sqlSessionFactory.setConfigLocation(resourcePatternResolver.getResource(configLocation));
        sqlSessionFactory.setPlugins(interceptors);
        SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
        sessionFactory.getConfiguration().setDefaultExecutorType(ExecutorType.REUSE);
        log.info("config location: {},mapper locations: {}", configLocation, mapperLocations);
        return sessionFactory;
    }


    @Bean("sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean("batchSqlSessionTemplate")
    public SqlSessionTemplate batchSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

}
