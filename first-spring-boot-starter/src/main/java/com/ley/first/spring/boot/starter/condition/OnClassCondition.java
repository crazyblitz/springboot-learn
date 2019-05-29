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

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";


    private boolean match = true;

    /**
     * use fast-fail
     **/
    public boolean matches0(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
        String[] classNames = (String[]) annotationAttributes.get("name");
        String[] basePackages = (String[]) annotationAttributes.get("basePackages");
        List<String> classNameList = CollectionUtils.arrayToList(classNames);
        boolean[] matches = new boolean[classNames.length];
        //初始化为false
        initMatches(matches);
        if (!CollectionUtils.isEmpty(classNameList)) {
            classNameList.stream().forEach(className -> {
                ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(context.getResourceLoader());
                try {
                    for (String basePackage : basePackages) {
                        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
                        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage, context) + "/" + DEFAULT_RESOURCE_PATTERN;
                        Resource[] resources = resolver.getResources(packageSearchPath);
                        int matchIndex = 0;
                        for (Resource resource : resources) {
                            if (resource.isReadable()) {
                                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                                if (classMetadata.getClassName().equalsIgnoreCase(className)) {
                                    matches[matchIndex++] = true;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    log.error("I/O failure during classpath scanning: {}", e.getMessage());
                }
            });
        }
        int matchLength = matches.length;
        for (int i = 0; i < matchLength; i++) {
            if (!matches[i]) {
                this.match = false;
                break;
            }
        }
        return match;
    }

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

    private String resolveBasePackage(String basePackage, ConditionContext context) {
        return ClassUtils.convertClassNameToResourcePath(context.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

    private void initMatches(boolean[] matches) {
        for (int i = 0; i < matches.length; i++) {
            matches[i] = false;
        }
    }


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
        String[] classNames = (String[]) annotationAttributes.get("name");
        List<String> classNameList = CollectionUtils.arrayToList(classNames);
        if (classNameList.isEmpty()) {
            for (String className : classNames) {
                if (!isPresent(className, null)) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }
}
