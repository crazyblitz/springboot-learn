package com.ley.spring.boot.embeded.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 19:53
 * @describe
 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.ley.spring.boot.embeded.web")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类配置
        WebMvcConfigurer.super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        //创建fast json配置
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty);

        converter.setFastJsonConfig(config);


        //处理fast json返回中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);

        //将自定义的配置信息放到视图解析器中
        converters.add(converter);
    }


}
