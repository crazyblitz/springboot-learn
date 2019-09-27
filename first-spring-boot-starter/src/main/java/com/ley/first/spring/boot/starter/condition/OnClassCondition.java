package com.ley.first.spring.boot.starter.condition;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ClassUtils.forName;

/**
 * 类描述:判断当前类路径是否存在指定类
 *
 * @author liuenyuan
 * @date 2019/4/27 20:09
 * @describe
 */
@Slf4j
public class OnClassCondition implements Condition {

    private static boolean isPresent(String className, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        try {
            forName(className, classLoader);
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
        String[] classNames = (String[]) annotationAttributes.get("name");
        List<String> classNameList = CollectionUtils.arrayToList(classNames);
        if (classNameList.isEmpty()) {
            for (String className : classNames) {
                if (!isPresent(className, ClassUtils.getDefaultClassLoader())) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }
}
