package com.ley.springboot.aop.advisor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
public class LogAdvisorConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static LogAdvisorPostProcessor logAdvisorPostProcessor() {
        return new LogAdvisorPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogPrinterAdvisor logPrinterAdvisor() {
        return new LogPrinterAdvisor();
    }
}
