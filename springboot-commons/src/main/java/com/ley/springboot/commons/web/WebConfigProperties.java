package com.ley.springboot.commons.web;

import com.ley.springboot.commons.utils.StringUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * web config properties
 *
 * @author liuenyuan
 **/
@CommonsLog
public class WebConfigProperties {

    private static final String WEB_CONFIG_LOCATION = "classpath:config/web.properties";

    private static final PathMatchingResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    private static final Properties WEB_PROPERTIES = new Properties();

    static {
        Resource resource = RESOURCE_PATTERN_RESOLVER.getResource("classpath:config/web.properties");
        try {
            WEB_PROPERTIES.load(resource.getInputStream());
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                log.error("classpath location config/web.properties is not found.");
            }
        }
    }

    public static String getProperty(String key) {
        return WEB_PROPERTIES.getProperty(key);
    }


    public static List<String> getPropertyAsList(String key) {
        return StringUtils.stringToList(getProperty(key));
    }

    public static void main(String[] args) {
        System.out.println(getPropertyAsList("requestFilter.ignoreList"));
        System.out.println(getPropertyAsList("fileUpload.allowFiles"));
    }
}
