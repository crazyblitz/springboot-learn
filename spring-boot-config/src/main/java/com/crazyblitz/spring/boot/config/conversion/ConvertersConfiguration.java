package com.crazyblitz.spring.boot.config.conversion;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.Collections;

/**
 * @author liuenyuan
 **/
@Configuration
@ConditionalOnNotWebApplication
public class ConvertersConfiguration {

    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean conversionServiceFactory() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToDateConverter()));
        return conversionServiceFactoryBean;
    }
}
