package com.ley.spring.customized.annotation.aware;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * @author liuenyuan
 **/
@Slf4j
public class FirstApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    public static final String JDBC_PROPERTY_SOURCE_NAME = "jdbcProperties";

    private int order = Ordered.LOWEST_PRECEDENCE;

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }


    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        applicationContext.setId("刘恩源");
        try {
            Resource resource = new ClassPathResource("jdbc.properties");
            applicationContext.getEnvironment().getPropertySources().addFirst(new PropertiesPropertySource(JDBC_PROPERTY_SOURCE_NAME,
                    PropertiesLoaderUtils.loadProperties(resource)));
        } catch (IOException e) {
            throw new BeanInitializationException(e.getMessage(), e);
        }
        log.info(applicationContext.getId());
    }
}
