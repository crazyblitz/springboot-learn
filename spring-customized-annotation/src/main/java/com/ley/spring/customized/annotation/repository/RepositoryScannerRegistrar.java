package com.ley.spring.customized.annotation.repository;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author liuenyuan
 **/
public class RepositoryScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {

    private ResourceLoader resourceLoader;

    private BeanFactory beanFactory;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取注解上属性
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RepositoryScan.class.getName()));
        ClassPathRepositoryScanner scanner = new ClassPathRepositoryScanner(registry);

        // this check is needed in Spring 3.1
        Optional.ofNullable(resourceLoader).ifPresent(scanner::setResourceLoader);


        Class<? extends Annotation> annotationClass = annotationAttributes.getClass("annotationClass");

        if (!Annotation.class.equals(annotationClass)) {
            scanner.setAnnotationClass(annotationClass);
        }

        List<String> basePackages = new ArrayList<>();

        //since jdk1.8
        basePackages.addAll(
                Arrays.stream(annotationAttributes.getStringArray("value"))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toList()));

        basePackages.addAll(
                Arrays.stream(annotationAttributes.getStringArray("basePackages"))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toList()));

        basePackages.addAll(
                Arrays.stream(annotationAttributes.getClassArray("basePackageClasses"))
                        .map(ClassUtils::getPackageName)
                        .collect(Collectors.toList()));

        //if base packages is empty,use default package
        if (basePackages.isEmpty()) {
            basePackages.addAll(AutoConfigurationPackages.get(beanFactory));
        }

        scanner.registerFilters();
        scanner.doScan(StringUtils.toStringArray(basePackages));
    }


}
