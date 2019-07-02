package com.crazyblitz.spring.boot.config.annotation.processor;

import com.crazyblitz.spring.boot.config.annotation.YamlPropertySource;
import com.crazyblitz.spring.boot.config.annotation.YamlPropertySources;
import com.crazyblitz.spring.boot.config.annotation.factory.YamlPropertySourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link org.springframework.context.annotation.ConfigurationClassParser#processPropertySource(AnnotationAttributes)
 * and {@link org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass}
 *
 * @author liuenyuan
 **/
@Slf4j
@Configuration(value = YamlPropertySourceAnnotationPostProcessor.BEAN_NAME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class YamlPropertySourceAnnotationPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements EnvironmentAware, ResourceLoaderAware {

    public final static String BEAN_NAME = "yamlPropertySourceAnnotationPostProcessor";

    private Environment environment;

    private ResourceLoader resourceLoader;

    private final List<String> propertySourceNames = new ArrayList<>();

    private static final PropertySourceFactory DEFAULT_PROPERTY_SOURCE_FACTORY = new YamlPropertySourceFactory();

    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment, "environment must be instance of ConfigurableEnvironment.");
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Assert.notNull(resourceLoader, "resourceLoader must not be null.");
        this.resourceLoader = resourceLoader;
    }


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // Process any @YamlPropertySource annotations
        Set<YamlPropertySource> yamlPropertySources = AnnotationUtils.getRepeatableAnnotations(bean.getClass(),
                YamlPropertySource.class, YamlPropertySources.class);
        if (!yamlPropertySources.isEmpty()) {
            Set<AnnotationAttributes> attributesSet = new LinkedHashSet<>(yamlPropertySources.size());
            yamlPropertySources.forEach(yamlPropertySource -> {
                AnnotationAttributes attributes = AnnotationUtils.getAnnotationAttributes(bean.getClass(),
                        yamlPropertySource);
                attributesSet.add(attributes);
            });

            for (AnnotationAttributes propertySource : attributesSet) {
                if (this.environment instanceof ConfigurableEnvironment) {
                    processPropertySource(propertySource, bean);
                } else {
                    log.warn("Ignoring @YamlPropertySource annotation on [" + bean.getClass() +
                            "]. Reason: Environment must implement ConfigurableEnvironment");
                }
            }
        }
        return true;
    }

    private void processPropertySource(AnnotationAttributes propertySource, Object bean) {
        String name = propertySource.getString("name");
        if (!StringUtils.hasLength(name)) {
            name = null;
        }
        String encoding = propertySource.getString("encoding");
        if (!StringUtils.hasLength(encoding)) {
            encoding = null;
        }
        String[] locations = propertySource.getStringArray("value");
        Assert.isTrue(locations.length > 0, "At least one @YamlPropertySource(value) location is required");
        boolean ignoreResourceNotFound = propertySource.getBoolean("ignoreResourceNotFound");

        Class<? extends PropertySourceFactory> factoryClass = propertySource.getClass("factory");
        PropertySourceFactory factory = (factoryClass == PropertySourceFactory.class ?
                DEFAULT_PROPERTY_SOURCE_FACTORY : BeanUtils.instantiateClass(factoryClass));

        for (String location : locations) {
            try {
                //处理占位符${...}
                String resolvedLocation = this.environment.resolveRequiredPlaceholders(location);
                Resource resource = this.resourceLoader.getResource(resolvedLocation);
                if (resource.getInputStream() != null) {
                    addPropertySource(factory.createPropertySource(name, new EncodedResource(resource, encoding)));
                }
            } catch (Throwable ex) {
                // Placeholders not resolvable or resource not found when trying to open it
                if (ignoreResourceNotFound) {
                    if (log.isInfoEnabled()) {
                        log.info("Properties or Yml or Yaml location [" + location + "] not resolvable: " + ex.getMessage());
                    }
                } else {
                    throw new BeanDefinitionStoreException("Failed to create bean [" + bean.getClass() + "]", ex);
                }
            }
        }
    }

    private void addPropertySource(PropertySource<?> propertySource) {
        String name = propertySource.getName();
        MutablePropertySources propertySources = ((ConfigurableEnvironment) this.environment).getPropertySources();

        if (this.propertySourceNames.contains(name)) {
            // We've already added a version, we need to extend it
            PropertySource<?> existing = propertySources.get(name);
            if (existing != null) {
                PropertySource<?> newSource = (propertySource instanceof ResourcePropertySource ?
                        ((ResourcePropertySource) propertySource).withResourceName() : propertySource);
                if (existing instanceof CompositePropertySource) {
                    ((CompositePropertySource) existing).addFirstPropertySource(newSource);
                } else {
                    if (existing instanceof ResourcePropertySource) {
                        existing = ((ResourcePropertySource) existing).withResourceName();
                    }
                    CompositePropertySource composite = new CompositePropertySource(name);
                    composite.addPropertySource(newSource);
                    composite.addPropertySource(existing);
                    propertySources.replace(name, composite);
                }
                return;
            }
        }

        if (this.propertySourceNames.isEmpty()) {
            propertySources.addLast(propertySource);
        } else {
            String firstProcessed = this.propertySourceNames.get(this.propertySourceNames.size() - 1);
            propertySources.addBefore(firstProcessed, propertySource);
        }
        this.propertySourceNames.add(name);
    }
}
