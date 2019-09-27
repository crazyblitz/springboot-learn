package com.ley.first.spring.boot.starter.condition;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.lang.NonNull;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aliue
 */
@Slf4j
public class OnClassCondition2 implements Condition {

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";


    private boolean match = true;


    private final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

    /**
     * use fast-fail
     **/
    @Override
    public boolean matches(@NonNull ConditionContext context, @NonNull AnnotatedTypeMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnClass2.class.getName()));
        String[] classNames = attributes.getStringArray("name");
        List<String> classNameList = CollectionUtils.arrayToList(classNames);
        List<String> basePackageList = Lists.newArrayListWithExpectedSize(16);
        boolean[] matches = new boolean[classNames.length];

        Arrays.fill(matches, false);
        if (!CollectionUtils.isEmpty(classNameList)) {
            classNameList.forEach(className -> {
                basePackageList.add(className.substring(0, className.lastIndexOf(".")));
                if (log.isInfoEnabled()) {
                    log.info("scan base package list: {}", basePackageList);
                }
                ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(context.getResourceLoader());
                try {
                    for (String basePackage : basePackageList) {
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
                    log.error("params: {},exception message: {},exception stack: {}",
                            Arrays.asList(context, metadata), e.getMessage(), e);
                }
            });
        }

        for (boolean match : matches) {
            if (!match) {
                this.match = false;
                break;
            }
        }
        return match;
    }

    private String resolveBasePackage(String basePackage, ConditionContext context) {
        return ClassUtils.convertClassNameToResourcePath(context.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }
}
