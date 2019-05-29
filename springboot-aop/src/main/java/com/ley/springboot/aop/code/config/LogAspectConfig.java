package com.ley.springboot.aop.code.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan("com.ley.springboot.aop.code")
public class LogAspectConfig {
}
