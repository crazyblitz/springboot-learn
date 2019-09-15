package com.crazyblitz.springboot.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SwaggerProperties.SWAGGER_PREFIX)
@Data
public class SwaggerProperties {

    public static final String SWAGGER_PREFIX = "spring.swagger";

    private String title;
    private String version;
}
