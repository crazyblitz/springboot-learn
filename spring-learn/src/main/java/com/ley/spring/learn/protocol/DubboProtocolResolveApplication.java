package com.ley.spring.learn.protocol;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/7 14:40
 * @describe
 */
public class DubboProtocolResolveApplication {

    public static void main(String[] args) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        DubboProtocolResolver resolver = new DubboProtocolResolver();
        resourceLoader.addProtocolResolver(resolver);
        Resource resource = resourceLoader.getResource("dubbo://jdbc.properties");
        System.out.println(PropertiesLoaderUtils
                .loadProperties(resource));
    }
}
