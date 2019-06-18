package com.crazyblitz.spring.boot.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 类描述: yml properties configuration
 *
 * @author liuenyuan
 * @date 2019/6/16 19:53
 * @describe
 */
public class YamlPropertiesConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
        yml.setResources(new ClassPathResource("/hello.yml"));
        configurer.setProperties(yml.getObject());
        return configurer;
    }
}
