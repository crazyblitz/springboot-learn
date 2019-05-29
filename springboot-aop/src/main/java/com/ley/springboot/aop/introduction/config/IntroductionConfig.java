package com.ley.springboot.aop.introduction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.ley.springboot.aop.introduction")
public class IntroductionConfig {
}
