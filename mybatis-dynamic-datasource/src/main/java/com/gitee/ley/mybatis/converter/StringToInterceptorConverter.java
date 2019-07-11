package com.gitee.ley.mybatis.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuenyuan
 **/
@Slf4j
@Component
public class StringToInterceptorConverter implements Converter<String, List<Interceptor>> {

    @Override
    public List<Interceptor> convert(String source) {
        String[] interceptors = StringUtils.tokenizeToStringArray(source, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        List<Interceptor> interceptorList = new ArrayList<>(interceptors.length);
        for (String interceptorClassName : interceptors) {
            try {
                Class<Interceptor> interceptorClass = (Class<Interceptor>) Class.forName(interceptorClassName);
                interceptorList.add(BeanUtils.instantiateClass(interceptorClass));
            } catch (ClassNotFoundException e) {
                log.warn("plugin class: {} not exist in classpath.", interceptorClassName);
            }
        }
        return interceptorList;
    }
}
