package com.ley.spring.customized.annotation.repository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liuenyuan
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 **/
@Slf4j
public class ClassPathRepositoryScanner extends ClassPathBeanDefinitionScanner {

    private static final Class<Repository> DEFAULT_ANNOTATION_CLASS = Repository.class;

    private Class<? extends Annotation> annotationClass = DEFAULT_ANNOTATION_CLASS;


    public ClassPathRepositoryScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }


    /**
     * Configures parent scanner to search for the right interfaces. It can search
     * for all interfaces or just for those that extends a markerInterface or/and
     * those annotated with the annotationClass
     */
    public void registerFilters() {

        //accept all class but not interface
        boolean acceptAllClass = true;

        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllClass = false;
        }

        //accept all class but not interface
        if (acceptAllClass) {
            // default include filter that accepts all classes
            addIncludeFilter((metadataReader, metadataReaderFactory) ->
                    metadataReader.getClassMetadata().isIndependent() && (!metadataReader.getClassMetadata().isInterface())
            );
        }

        // exclude package-info.java
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }


    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        boolean isDebugEnabled = log.isDebugEnabled();
        if (isDebugEnabled) {
            beanDefinitions.stream().map(BeanDefinitionHolder::getBeanName).collect(Collectors.toList())
                    .forEach(beanName -> log.info(beanName));
        }
        return beanDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isIndependent() && (!beanDefinition.getMetadata()
                .isInterface());
    }
}
