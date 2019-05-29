package com.ley.formatter.autoconfigure;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:29
 * @describe formatter enable auto configuration
 */
@Configuration
@ConditionalOnProperty(prefix = "formatter", name = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnResource(resources = "META-INF/spring.factories")
@ConditionalOnNotWebApplication
@ConditionalOnExpression("${formatter.enabled:true}")
public class FormatterAutoConfiguration {

    /**
     * 构建 {@link DefaultFormatter} Bean
     *
     * @return {@link DefaultFormatter}
     */
    @Bean
    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    /**
     * JSON 格式 {@link Formatter} Bean
     *
     * @return {@link JsonFormatter}
     */
    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    @ConditionalOnMissingBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }
}
