package com.ley.springboot.aop.aspect;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@ConditionalOnWebApplication
@Configuration
public class NeedLoginAdvisorConfig {

    @Resource
    private Gson gson;

    @Bean
    public NeedLoginAttributeSourceAdvisor needLoginAttributeSourceAdvisor() {
        return new NeedLoginAttributeSourceAdvisor(gson);
    }
}
