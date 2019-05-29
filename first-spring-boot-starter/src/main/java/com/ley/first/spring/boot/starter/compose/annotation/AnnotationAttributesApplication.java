package com.ley.first.spring.boot.starter.compose.annotation;

import org.junit.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;
import java.util.Map;

/**
 * 类描述:注解属性抽象{@link org.springframework.core.annotation.AnnotationAttributes}
 *
 * @author liuenyuan
 * @date 2019/4/26 15:11
 * @describe
 * @see org.springframework.core.annotation.AnnotationAttributes
 * @see AnnotatedElementUtils
 * @see org.springframework.core.annotation.AnnotationUtils
 */
public class AnnotationAttributesApplication {

    @Test
    public void testAnnotationAttributes() {
        //测试AnnotatedElementUtils.getMergedAnnotationAttributes()符合属性别名完整语义
//        AnnotatedElement annotatedElement = TransactionalService.class;

        AnnotatedElement annotatedElement = TransactionServiceBean.class;

        //获取@Service注解属性独享
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Service.class);

        //获取@Transactional注解属性独享
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Transactional.class);



        //输出
        print(serviceAttributes);
        print(transactionalAttributes);
    }


    @Test
    public void testAnnotationUtils() {
        Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(TransactionServiceBean
                .class.getAnnotation(TransactionalService.class));
        attributes.forEach((key, value) -> System.out.println(key + ":" + value));
    }


    private void print(AnnotationAttributes annotationAttributes) {
        System.out.printf("注解 @%s 属性集合: \n",
                annotationAttributes.annotationType().getName());

        annotationAttributes.forEach((name, value) -> System.out.printf("\t属性 %s : %s \n",
                name, value));
    }


}
