package com.ley.first.spring.boot.starter.metadata.reader;

import com.ley.first.spring.boot.starter.compose.annotation.TransactionServiceBean;
import org.junit.Test;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.util.Arrays;
import java.util.Set;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/25 19:51
 * @describe metadata reader
 * @see org.springframework.core.type.classreading.MetadataReaderFactory
 * @see org.springframework.core.type.classreading.CachingMetadataReaderFactory
 * @see org.springframework.core.type.classreading.SimpleMetadataReaderFactory
 * @see org.springframework.core.type.classreading.MetadataReader
 * @see org.springframework.core.type.classreading.SimpleMetadataReader
 * @see org.springframework.core.type.classreading.ClassMetadataReadingVisitor
 * @see org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor
 * @see org.springframework.core.type.classreading.AnnotationAttributesReadingVisitor
 * @see org.springframework.core.type.classreading.MethodMetadataReadingVisitor
 * @see org.springframework.core.type.StandardClassMetadata
 * @see org.springframework.core.type.StandardAnnotationMetadata
 * @see org.springframework.core.type.StandardMethodMetadata
 * @see org.springframework.core.annotation.AnnotationAttributes
 * @see org.springframework.core.annotation.AnnotationUtils
 * @see org.springframework.core.annotation.AnnotatedElementUtils
 * @since 4.0.0.RELEASE(递归多层遍历注解, 实现Component注解多层派生性)
 */
public class MetadataReaderTest {


    @Test
    public void testStandardAnnotationMetadata() {
        StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(TransactionServiceBean.class);
        //获取自省类上所有非Java注解
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
        annotationTypes.forEach(annotationType -> {
            //递归获取自省类所有非Java注解的元注解
            Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
            metaAnnotationTypes.forEach(metaAnnotationType -> {
                System.out.println(String.format("注解: %s;元标注: %s",
                        annotationType, metaAnnotationType));
            });
        });


        annotationTypes.forEach(annotationType -> {
            Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
            metaAnnotationTypes.forEach(metaAnnotationType -> {
                System.out.println(String.format("注解: %s,属性: %s;元标注: %s,属性: %s",
                        annotationType, annotationMetadata.getAnnotationAttributes(annotationType),
                        metaAnnotationType, annotationMetadata.getAnnotationAttributes(metaAnnotationType)));
            });
        });


        annotationTypes.forEach(annotationType -> {
            System.out.println(annotationMetadata.getAllAnnotationAttributes(annotationType));
        });

    }
}
