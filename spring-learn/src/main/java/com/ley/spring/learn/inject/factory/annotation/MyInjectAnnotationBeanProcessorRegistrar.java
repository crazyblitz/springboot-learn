package com.ley.spring.learn.inject.factory.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * @author liuenyuan
 **/
public class MyInjectAnnotationBeanProcessorRegistrar implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyInjectAnnotationBeanProcessor.class);
        AbstractBeanDefinition definition = definitionBuilder.getBeanDefinition();
        definition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        definition.setSource(null);
        registry.registerBeanDefinition(MyInjectAnnotationBeanProcessor.BEAN_NAME, definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
