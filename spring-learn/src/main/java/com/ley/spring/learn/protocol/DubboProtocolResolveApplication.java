package com.ley.spring.learn.protocol;

import org.springframework.core.io.Resource;
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
        DubboProtocolResolver resolver = new DubboProtocolResolver();
        Resource resource = resolver.resolve("dubbo://jdbc.properties", null);
        System.out.println(PropertiesLoaderUtils
                .loadProperties(resource));
    }
}
