package com.ley.first.spring.boot.starter.compose.annotation;

import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/26 9:49
 * @describe
 */
@TransactionalService(name = "test")
public class ComposeAnnotationReflectionApplication {


    /**
     * 利用Java反射API获取注解属性,并没有将反射进行到底
     **/
    @Test
    public void javaReflect() {
        //Class实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = ComposeAnnotationReflectionApplication.class;

        //从AnnotatedElement获取注解
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        //获取注解属性
        String nameAttribute = transactionalService.name();
        System.out.println("transactionService.name() = " + nameAttribute);
    }


    /**
     * 完全使用Java反射,需要将Annotation接口方法排除
     **/
    @Test
    public void springReflect() {
        //Class实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = ComposeAnnotationReflectionApplication.class;

        //从AnnotatedElement获取注解
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        //完全利用Java反射
        ReflectionUtils.doWithMethods(TransactionalService.class,
                method -> System.out.printf("@TransactionalService.%s() = %s\n",
                        method.getName(),
                        //执行Method反射调用
                        ReflectionUtils.invokeMethod(method,
                                //选择无参构造函数
                                transactionalService)), method -> !method.getDeclaringClass().equals(Annotation.class));
    }


    /**
     * 使用java反射递归遍历
     **/
    @Test
    public void springReflectRecruisve() {
        AnnotatedElement element = ComposeAnnotationReflectionApplication.class;

        TransactionalService transactionalService = element.getAnnotation(TransactionalService.class);

        Set<Annotation> metaAnnotations = getAllMetaAnnotations(transactionalService);

        metaAnnotations.forEach(ComposeAnnotationReflectionApplication::printAnnotationAttribute);
    }


    /**
     * 获取注解的所有元注解
     **/
    private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();

        if (ObjectUtils.isEmpty(metaAnnotations)) {
            return Collections.emptySet();
        }


        //获取所有非Java标准的元注解集合
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                //判处Java标注注解,@Target,@Document等,
                // 它们因相互依赖,导致递归不断
                // 排除java.lang.annotation包名
                .filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());


        //递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotations = metaAnnotationsSet.stream()
                .map(ComposeAnnotationReflectionApplication::getAllMetaAnnotations)
                .collect(HashSet::new, Set::addAll, Set::addAll);

        //递归添加结果
        metaAnnotationsSet.addAll(metaMetaAnnotations);

        return metaAnnotationsSet;
    }


    private static void printAnnotationAttribute(Annotation annotation) {
        Class<?> annotationType = annotation.annotationType();

        ReflectionUtils.doWithMethods(annotationType,
                method -> System.out.printf("%s.%s = %s\n", annotationType.getSimpleName(),
                        method.getName(),
                        ReflectionUtils.invokeMethod(method, annotation)),
                //选择无参构造方法,且非Annotation方法
                method -> method.getParameterCount() == 0 && !method.getDeclaringClass().equals(Annotation.class));
    }


    /**
     * @see org.springframework.core.type.StandardAnnotationMetadata
     **/
    @Test
    public void springStandardAnnotationMetadata() {
        //读取@TransactionalService AnnotationMetadata信息
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ComposeAnnotationReflectionApplication.class);

        //获取所有的元注解(全类名)集合
        Set<String> metaAnnotationTypes = annotationMetadata.getAnnotationTypes()
                .stream()
                //读取单注解元注解类型集合
                .map(annotationMetadata::getMetaAnnotationTypes)
                //合并元注解类型集合
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);


        annotationMetadata.getAnnotatedMethods(Test.class.getName())
                .forEach(methodMetadata -> {
                    System.out.println(methodMetadata.getMethodName());
                });


        System.out.println(StringUtils.arrayToDelimitedString(StringUtils.toStringArray(annotationMetadata.getMetaAnnotationTypes(TransactionalService.class.getName())),
                ","));
        System.out.println(Arrays.asList(annotationMetadata.getInterfaceNames()));

        //读取所有元注解类型
        metaAnnotationTypes.forEach(metaAnnotation -> {
            //读取元注解属性
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaAnnotation);
            if (!CollectionUtils.isEmpty(annotationAttributes)) {
                annotationAttributes.forEach((name, value) -> System.out.printf("注解 %s 属性 %s = %s\n",
                        ClassUtils.getShortName(metaAnnotation), name, value));
            }
        });

    }
}
