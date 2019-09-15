package com.crazyblitz.springboot.shiro.config;

import com.crazyblitz.springboot.shiro.utils.web.SwaggerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 com.wx.tourism.config
 **/
@Configuration
@Slf4j
@EnableSwagger2
@EnableConfigurationProperties({SwaggerProperties.class})
public class Swagger2Config {

    private static final String SWAGGER_PREFIX = "spring.swagger.";

    private final SwaggerProperties swaggerProperties;

    public Swagger2Config(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
        if (log.isInfoEnabled()) {
            log.info(ClassUtils.getShortName(this.getClass()) + ": {}", swaggerProperties);
        }
    }

    @Bean
    public Docket docket() {
        SwaggerUtils.setTitle(swaggerProperties.getTitle());
        SwaggerUtils.setVersion(swaggerProperties.getVersion());
        return SwaggerUtils.initDocket();
    }
}
