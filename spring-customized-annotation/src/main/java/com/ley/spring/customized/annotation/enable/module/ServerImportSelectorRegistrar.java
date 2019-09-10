package com.ley.spring.customized.annotation.enable.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:34
 * @describe
 */
@Slf4j
public class ServerImportSelectorRegistrar implements ImportBeanDefinitionRegistrar {

    private final BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableServerRegistrar.class.getName()));
        Server.Type type = annotationAttributes.getEnum("type");
        log.info(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        switch (type) {
            case HTTP:
                BeanDefinition httpDefinition = BeanDefinitionBuilder.genericBeanDefinition(HttpServer.class).getBeanDefinition();
                registry.registerBeanDefinition(beanNameGenerator.generateBeanName(httpDefinition, registry),
                        httpDefinition);
                break;
            case FTP:
                BeanDefinition ftpDefinition = BeanDefinitionBuilder.genericBeanDefinition(FtpServer.class).getBeanDefinition();
                registry.registerBeanDefinition(beanNameGenerator.generateBeanName(ftpDefinition, registry),
                        ftpDefinition);
                break;
            default:
                BeanDefinition defaultDefinition = BeanDefinitionBuilder.genericBeanDefinition(HttpServer.class).getBeanDefinition();
                registry.registerBeanDefinition(beanNameGenerator.generateBeanName(defaultDefinition, registry),
                        defaultDefinition);
                break;
        }
    }


    /**
     * register bean definition with {@link BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition, BeanDefinitionRegistry)}
     * @see BeanDefinitionReaderUtils
     **/
    public void registerBeanDefinitionsWithBeanDefinitionReaderUtils(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableServerRegistrar.class.getName()));
        Server.Type type = annotationAttributes.getEnum("type");
        switch (type) {
            case HTTP:
                AbstractBeanDefinition httpDefinition = BeanDefinitionBuilder.genericBeanDefinition(HttpServer.class).getBeanDefinition();
                BeanDefinitionReaderUtils.registerWithGeneratedName(httpDefinition, registry);
                break;
            case FTP:
                AbstractBeanDefinition ftpDefinition = BeanDefinitionBuilder.genericBeanDefinition(FtpServer.class).getBeanDefinition();
                BeanDefinitionReaderUtils.registerWithGeneratedName(ftpDefinition, registry);
                break;
            default:
                AbstractBeanDefinition defaultDefinition = BeanDefinitionBuilder.genericBeanDefinition(HttpServer.class).getBeanDefinition();
                BeanDefinitionReaderUtils.registerWithGeneratedName(defaultDefinition, registry);
                break;
        }
    }

}
