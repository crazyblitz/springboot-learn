package com.crazyblitz.spring.boot.config.conversion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuenyuan
 * @see org.springframework.core.convert.ConversionService
 * @see org.springframework.core.convert.converter.Converter
 * @see org.springframework.format.Formatter
 * @see org.springframework.core.convert.converter.ConverterRegistry
 * @see org.springframework.format.FormatterRegistrar
 * @see org.springframework.core.convert.support.ConversionServiceFactory
 * @see org.springframework.context.support.ConversionServiceFactoryBean
 * @see org.springframework.core.convert.support.DefaultConversionService
 * @see org.springframework.format.support.DefaultFormattingConversionService
 * @see org.springframework.core.convert.converter.ConditionalConverter
 * @see org.springframework.core.convert.converter.ConditionalGenericConverter
 * @see org.springframework.core.convert.support.GenericConversionService
 * @see org.springframework.core.convert.converter.GenericConverter
 * @see org.springframework.core.convert.support.ConfigurableConversionService
 **/
@SpringBootApplication
public class ConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversionServiceApplication.class, args);
    }
}
