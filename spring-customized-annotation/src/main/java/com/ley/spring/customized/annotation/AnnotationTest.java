package com.ley.spring.customized.annotation;

import com.ley.spring.customized.annotation.business.OrderRepository;
import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/6/1 9:42
 * @describe
 */
public class AnnotationTest {

    @Test
    public void testAnnotationMetadata() throws IOException {
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(OrderRepository
                .class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
        annotationTypes.forEach(
                annotationType -> annotationMetadata.getMetaAnnotationTypes(annotationType)
                        .forEach(metaAnnotationType -> System.out.println(String.format("注解: %s,元标注: %s",
                                annotationType, metaAnnotationType))));
    }
}
