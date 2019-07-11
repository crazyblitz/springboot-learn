package com.ley.spring.customized.annotation.env;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/20 10:07
 * @describe
 */
@Component
@Slf4j
public class EnvironmentTest implements EnvironmentAware{

    private Environment environment;



    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    /**
     * System environment property source name: {@value}
     **/
    public void getSystemEnvironment() {
        log.info("system properties");
        if (this.environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            MapPropertySource systemEnvironment = new MapPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                    configurableEnvironment.getSystemProperties());
            String[] propertyNames = systemEnvironment.getPropertyNames();
            for (String propertyName : propertyNames) {
                log.info("property name: {},property value: {}",
                        propertyName, systemEnvironment.getProperty(propertyName));
            }
        }
    }


    public void getSystemProperties() {
        log.info("jvm properties");
        if (this.environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            MapPropertySource systemProperties = new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
                    configurableEnvironment.getSystemProperties());
            String[] propertyNames = systemProperties.getPropertyNames();
            for (String propertyName : propertyNames) {
                log.info("property name: {},property value: {}",
                        propertyName, systemProperties.getProperty(propertyName));
            }
        }
    }


    /**
     * 获取默认的spring boot application配置
     **/
    public void getSpringBootEnvironment() {
        log.info("spring boot config properties.");
        String applicationConfigName = "applicationConfig: [classpath:/application.properties]";
        ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
        PropertySource<?> propertySource = propertySources.get(applicationConfigName);
        log.info("source:{}", propertySource.getSource());
        propertySources.forEach(propertySource1 -> log.info("source: {}", propertySource1.getSource()));
    }
}
