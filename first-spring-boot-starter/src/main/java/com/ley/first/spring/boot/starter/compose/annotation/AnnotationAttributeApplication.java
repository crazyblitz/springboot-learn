package com.ley.first.spring.boot.starter.compose.annotation;

import org.junit.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

/**
 * 类描述:Spring注解属性抽象AnnotationAttributes
 *
 * @author liuenyuan
 * @date 2019/4/27 10:39
 * @describe
 * @see org.springframework.core.annotation.AnnotationAttributes
 */
public class AnnotationAttributeApplication {


    /**
     * Spring注解属性覆盖
     * <br/>
     * 多层次元注解场景存在限制,根本原因在于Java注解的静态性.
     * <br/>
     * 较低层次的注解属性将覆盖较高层.<b>(AnnotationAttributes采用注解就近覆盖设计原则)</b>
     * <br/>
     **/
    @Test
    public void annotationAttributesOverride() {
        AnnotatedElement element = TransactionalService.class;

        AnnotationAttributes serviceAnnotationAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(element, Service.class);

        AnnotationAttributes transactionalAnnotationAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(element, Transactional.class);

        print0(serviceAnnotationAttributes);

        print0(transactionalAnnotationAttributes);
    }


    private static void print0(AnnotationAttributes annotationAttributes) {
        System.out.printf("注解 %s 属性集合 : \n", annotationAttributes.annotationType().getName());

        annotationAttributes.forEach((name, value) -> System.out.printf("\t 属性 %s : %s \n",
                name, value));


    }
}
