package com.ley.first.spring.boot.starter;


import com.ley.formatter.autoconfigure.Formatter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:34
 * @describe
 * @see org.springframework.core.type.classreading.AnnotationAttributesReadingVisitor
 * @see org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor
 * @see org.springframework.core.type.classreading.ClassMetadataReadingVisitor
 * @see org.springframework.core.type.classreading.MethodMetadataReadingVisitor
 * @see org.springframework.core.type.classreading.CachingMetadataReaderFactory
 * @see org.springframework.core.type.classreading.SimpleMetadataReaderFactory
 * @see org.springframework.core.type.classreading.SimpleMetadataReader
 * @since 4.0.0.RELEASE(实现递归获取元注解)
 */
@SpringBootApplication
public class FormatterEnableAutoConfigurationApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FormatterEnableAutoConfigurationApplication.class)
                .web(WebApplicationType.NONE)
                .properties("formatter.enabled=true")
                .run(args);
        // 待格式化对象
        Map<String, Object> data = new HashMap<>(2);
        data.put("name", "小马哥");
        // 获取 Formatter 来自于 FormatterAutoConfiguration
        Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
        if (beans.isEmpty()) {
            throw new NoSuchBeanDefinitionException(Formatter.class);
        }
        beans.forEach((beanName, formatter) ->
                System.out.printf("[Bean name : %s] %s.format(data) : %s\n", beanName, formatter.getClass().getSimpleName(),
                        formatter.format(data))
        );


        AutoConfigurationPackages.get(context.getBeanFactory()).forEach(System.out::println);


        //  关闭当前上下文
        context.close();
    }
}
