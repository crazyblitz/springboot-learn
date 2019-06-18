package com.ley.first.spring.boot.starter.spring.factories;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/29 23:21
 * @describe
 */
@EnableConfiguration
@ComponentScan(basePackages = "com.ley.first.spring.boot.starter.spring.factories")
public class SpringFactoriesApplication {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringFactoriesApplication.class);
        List<String> beanNames = CollectionUtils.arrayToList(ctx.getBeanDefinitionNames());
        beanNames.forEach(System.out::println);
        System.out.println(ctx.getBean("user"));


        Class<?> clazz = ClassUtils.forName("org.springframework.context.annotation.ConfigurationClassUtils",
                ClassUtils.getDefaultClassLoader());
        Method method = ReflectionUtils.findMethod(clazz, "isFullConfigurationClass", BeanDefinition.class);
        ReflectionUtils.makeAccessible(method);
        BeanDefinition beanDefinition = ctx.getBeanDefinition("userConfiguration");
        List<String> attributeNames = Arrays.asList(beanDefinition
                .attributeNames()).stream().collect(Collectors.toList());
        attributeNames.stream().map(name -> beanDefinition.getAttribute(name))
                .collect(Collectors.toSet()).forEach(System.out::println);

        System.out.println(method.invoke(clazz, ctx.getBeanDefinition("userConfiguration")));
        ctx.close();
    }
}
