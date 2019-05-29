package com.ley.springboot.autoconfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.StringUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "com.ley.springboot.autoconfig",
        exclude = {DataSourceAutoConfiguration.class})
@ComponentScan
public class AnnotationMetaTest {

    public static void main(String[] args) {
        AnnotatedElement annotatedElement = AnnotationMetaTest.class;
        AnnotationAttributes transactionalAttributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, SpringBootApplication.class);
        print(transactionalAttributes);
        System.out.println(StringUtils.capitalize("user"));
    }


    private static void print(AnnotationAttributes annotationAttributes) {
        annotationAttributes.forEach((key, value) -> {
            if (value instanceof String[]) {
                value = annotationAttributes.getStringArray(key);
                System.out.println(String.format(key + ":" + Arrays.stream((String[]) value)
                        .reduce("", (str1, str2) -> str1.concat(str2))));
            } else if (value instanceof Class[]) {
                value = annotationAttributes.getClassArray(key);
                System.out.println(String.format(key + ":" + Arrays.stream((Class<?>[]) value)
                        .collect(Collectors.toList())));
            } else {
                System.out.println(String.format(key + ":" + value));
            }
        });
    }

}
