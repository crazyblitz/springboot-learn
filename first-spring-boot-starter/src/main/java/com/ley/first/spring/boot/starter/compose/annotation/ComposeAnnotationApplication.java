package com.ley.first.spring.boot.starter.compose.annotation;

import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * 类描述: 组合注解启动类
 *
 * @author liuenyuan
 * @date 2019/4/25 20:14
 * @describe
 */
@TransactionalService
public class ComposeAnnotationApplication {

    public static void main(String[] args) throws IOException {
        String className = ComposeAnnotationApplication.class.getName();

        //构建MetadataReaderFactory
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

        //读取@TransactionalService MetadataReader信息
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);

        //读取@TransactionalService AnnotationMetadata信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        annotationMetadata.getAnnotationTypes().forEach(annotationType -> {
            Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);

            metaAnnotationTypes.forEach(metaAnnotationType -> System.out.println(String.format(
                    "注解 %s 元标注; %s\n", annotationType, metaAnnotationType)));
        });

    }


    @Test
    public void testStandardAnnotationMetadataReader() {
        Class<?> introspectedClass = ComposeAnnotationReflectionApplication.class;
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(introspectedClass);
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
        System.out.println(annotationTypes);

        annotationMetadata.getAnnotationTypes().forEach(annotationType -> {
            Set<String> metadataAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
            //获取注解上属性
            System.out.println(annotationMetadata.getAllAnnotationAttributes(annotationType));
            metadataAnnotationTypes.forEach(metadataAnnotationType -> {
                //获取注解上元注解属性
                System.out.println(annotationMetadata.getAllAnnotationAttributes(metadataAnnotationType));

                System.out.println(String.format(
                        "注解 %s 元标注; %s\n", annotationType, metadataAnnotationType));
            });
        });
    }
}
