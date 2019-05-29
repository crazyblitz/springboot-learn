package com.ley.spring.learn.protocol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.net.URL;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/7 14:33
 * @describe
 */
@Slf4j
public class DubboProtocolResolver implements ProtocolResolver {

    private static final String DUBBO_PROTOCOL_PREFIX = "dubbo://";

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(DUBBO_PROTOCOL_PREFIX)) {
            String realLocation = location.substring(DUBBO_PROTOCOL_PREFIX.length());
            if (log.isDebugEnabled()) {
                log.debug("real location: {}", realLocation);
            }
            ClassLoader classLoader = (resourceLoader == null) ? ClassLoader.getSystemClassLoader() : resourceLoader.getClassLoader();
            URL resourceUrl = classLoader.getResource(realLocation);
            UrlResource resource = new UrlResource(resourceUrl);
            return resource;
        }
        return null;
    }
}
