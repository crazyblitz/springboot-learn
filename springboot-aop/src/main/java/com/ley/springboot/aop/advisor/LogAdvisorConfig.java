package com.ley.springboot.aop.advisor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass({AbstractAutoProxyCreator.class})
@Slf4j
public class LogAdvisorConfig implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!registry.containsBeanDefinition(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME)) {
            BeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(DefaultAdvisorAutoProxyCreator.class).getBeanDefinition();
            definition.getPropertyValues().addPropertyValue("proxyTargetClass", Boolean.TRUE);
            registry.registerBeanDefinition(BeanDefinitionReaderUtils.generateBeanName(definition, registry), definition);
            log.info("registry bean name: {}", BeanDefinitionReaderUtils.generateBeanName(definition, registry));
        } else {
            log.info("spring bean factory already contains beanName: {}", AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
