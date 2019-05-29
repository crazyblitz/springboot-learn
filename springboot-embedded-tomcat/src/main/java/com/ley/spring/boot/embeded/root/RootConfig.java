package com.ley.spring.boot.embeded.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 19:53
 * @describe
 * @see org.springframework.web.context.ContextLoaderListener
 */
@Configuration
@ComponentScan(basePackages = {"com.ley.spring.boot.embeded.root.service"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {


}
