package com.crazyblitz.springboot.shiro.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@Slf4j
public class DruidDataSourceConfig {

    private final DataSourceProperties dataSourceProperties;

    public DruidDataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * datasource 1
     **/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        if(log.isInfoEnabled()){
            log.info("dataSource properties: {}", dataSourceProperties.toString());
        }
        return DruidDataSourceBuilder.create().build();
    }
}
