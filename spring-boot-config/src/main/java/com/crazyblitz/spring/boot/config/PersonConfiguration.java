package com.crazyblitz.spring.boot.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/6/16 13:44
 * @describe
 */
@EnableConfigurationProperties({PersonProperties.class, AppSystemProperties.class, AppIoProperties.class})
@Configuration
//@YamlPropertySource(encoding = "UTF-8", name = "hello", value = {"classpath:/hello.yml"})
public class PersonConfiguration {

    private final PersonProperties personProperties;

    private final AppSystemProperties appSystemProperties;

    private final AppIoProperties appIoProperties;

    public PersonConfiguration(PersonProperties personProperties, AppSystemProperties appSystemProperties,
                               AppIoProperties appIoProperties) {
        this.personProperties = personProperties;
        this.appSystemProperties = appSystemProperties;
        this.appIoProperties = appIoProperties;
        System.out.println(String.format("PersonProperties: %s,AppSystemProperties: %s," +
                        "AppIoProperties: %s", this.personProperties,
                this.appSystemProperties, this.appSystemProperties));
    }
}
