package com.crazyblitz.spring.boot.config.conversion;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;

import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * 值类型的转换器可以重用于数组和集合,前提标注集合是合适的.
 *
 * @author liuenyuan
 * @see org.springframework.format.support.DefaultFormattingConversionService
 * @see org.springframework.context.support.ConversionServiceFactoryBean
 * @see org.springframework.format.support.FormattingConversionServiceFactoryBean
 * @see org.springframework.core.convert.support.StringToArrayConverter
 * @see org.springframework.core.convert.support.StringToCollectionConverter
 * @see org.springframework.core.convert.support.CollectionToStringConverter
 * @see org.springframework.format.AnnotationFormatterFactory
 * @see org.springframework.format.number.NumberFormatAnnotationFormatterFactory
 * @see FormatterRegistry
 * @see org.springframework.format.FormatterRegistrar
 * @see org.springframework.format.annotation.NumberFormat
 **/
@Configuration
@Slf4j
public class WebConfiguration extends WebMvcConfigurationSupport {


    /**
     * @see org.springframework.core.convert.support.GenericConversionService#converters
     **/
    private final static String PROPERTY_CONVERTERS_NAME = "converters";

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        Map converters = getConvertersRegistered(registry);
        converters.forEach((key, value) -> System.out.println(key + "=" + value));
    }


    /**
     * get already registry converters in {@link org.springframework.core.convert.support.ConfigurableConversionService}
     **/
    private Map getConvertersRegistered(FormatterRegistry registry) {

        if (registry instanceof GenericConversionService) {
            GenericConversionService conversionService = (GenericConversionService) registry;
            Field convertersField = ReflectionUtils.findField(conversionService.getClass(), PROPERTY_CONVERTERS_NAME);
            assert convertersField != null;
            ReflectionUtils.makeAccessible(convertersField);
            try {
                Object converters = convertersField.get(conversionService);
                Field convertersRegisteredField = ReflectionUtils.findField(converters.getClass(),
                        PROPERTY_CONVERTERS_NAME);
                ReflectionUtils.makeAccessible(convertersRegisteredField);
                Object convertersRegistered = convertersRegisteredField.get(converters);

                if (convertersRegistered instanceof Map) {
                    return (Map) convertersRegistered;
                }

            } catch (IllegalAccessException ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }
        return null;
    }


}
