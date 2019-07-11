package com.gitee.ley.mybatis.config;

import com.gitee.ley.mybatis.converter.StringToInterceptorConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.Collections;

@Configuration
public class ConversionServiceConfiguration {


    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean conversionServiceFactory() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToInterceptorConverter()));
        return conversionServiceFactoryBean;
    }
}
