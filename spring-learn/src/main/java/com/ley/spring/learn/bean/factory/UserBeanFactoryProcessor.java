package com.ley.spring.learn.bean.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * 允许BeanFactoryPostProcessor在容器实例化任何其它bean之前读取配置元数据,并可以根据需要进行修改.
 * <p>
 * 加载了bean的定义文件之后,在bean实例化之前执行的.
 **/
@Slf4j
public class UserBeanFactoryProcessor implements BeanFactoryPostProcessor {

    /**
     * 绝对不允许在BeanFactoryPostProcessor中触发到bean的实例化
     * 第一:如果你使用BeanFactory作为Spring Bean的工厂类,则所有的bean都是在第一次使用该Bean的时候实例化
     * 第二:如果你使用ApplicationContext作为Spring Bean的工厂类,则又分为以下几种情况:
     * (1):如果bean的scope是singleton的,并且lazy-init为false(默认是false，所以可以不用设置),则ApplicationContext启动的时候就实例化该Bean，并且将实例化的Bean放在一个map结构的缓存中，下次再使用该Bean的时候，直接从这个缓存中取
     * (2):如果bean的scope是singleton的,并且lazy-init为true,则该Bean的实例化是在第一次使用该Bean的时候进行实例化
     * <p>
     * (3):如果bean的scope是prototype的，则该Bean的实例化是在第一次使用该Bean的时候进行实例化
     **/
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition("user");
        MutablePropertyValues pvs = beanDefinition.getPropertyValues();
        pvs.addPropertyValue("name", "刘恩源");
        pvs.addPropertyValue("age", "22");
        beanDefinition.setInitMethodName("init");
        beanDefinition.setDestroyMethodName("destroy");
        beanDefinition.setScope(ConfigurableListableBeanFactory.SCOPE_SINGLETON);
    }
}
