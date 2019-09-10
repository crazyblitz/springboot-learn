package com.ley.spring.customized.annotation.enable.module;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:23
 * @describe
 * @see ImportSelector
 * @see org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 */
public class ServerImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //读取EnableServer所有属性和方法
        //其中key为属性方法名称,value为属性方法返回对象
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableServerSelector.class.getName()));

        //获取名为"type"的属性方法,强制转换为Server.Type类型
        Server.Type type = annotationAttributes.getEnum("type");

        //导入类名称数组
        String[] importClassNames;
        switch (type) {
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
            default:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
        }
        return importClassNames;
    }
}
