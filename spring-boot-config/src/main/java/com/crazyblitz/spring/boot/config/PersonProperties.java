package com.crazyblitz.spring.boot.config;

import com.crazyblitz.spring.boot.config.annotation.YamlPropertySource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 类描述: load properties or yml file into java class
 *
 * @author liuenyuan
 * @date 2019/6/16 13:42
 * @describe
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @see org.springframework.core.env.PropertySource
 * @see org.springframework.core.env.MapPropertySource
 * @see org.springframework.core.env.PropertiesPropertySource
 * @see org.springframework.core.io.support.ResourcePropertySource
 */
@ConfigurationProperties(prefix = "person")
@YamlPropertySource(value = {"classpath:/hello2.yml"}, name = "hello", encoding = "UTF-8")
@Data
public class PersonProperties {

    private String name;

    private Integer age;

    private String school;
}
