package com.crazyblitz.spring.boot.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 类描述: spring boot config
 *
 * @author liuenyuan
 * @date 2019/6/16 11:36
 * @describe
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.context.annotation.PropertySource
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.core.env.Environment
 * @see org.springframework.context.annotation.Profile
 * @see org.springframework.context.support.PropertySourcesPlaceholderConfigurer
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(ConfigApplication.class);
        ConfigurableApplicationContext context = builder.run(args);
        HelloUser helloUser = context.getBean(HelloUser.class);
        System.out.println(String.format("通过@Value注解读取自定义的少量属性: %s,通过spEL表达式: %s", helloUser.getUserName(),
                helloUser.getSystemPropertiesName()));
        System.out.println(context.getBean(PersonProperties.class));
        System.out.println(context.getEnvironment().getPropertySources());
        System.out.println(context.getEnvironment().getPropertySources().get("hello"));
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        context.close();
    }
}
