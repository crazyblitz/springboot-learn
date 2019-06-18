package com.crazyblitz.spring.boot.config.annotation.processor;

import com.crazyblitz.spring.boot.config.annotation.YmlPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述: {@link YmlPropertySource} bean post processor.this class convert the yml or yaml file
 * {@link YmlPropertySource#value()} to {@link PropertiesPropertySource},and add the property source
 * named {@link YmlPropertySource#name()} into {@link Environment}.When you use this annotation,you
 * must for follow example:
 * <pre>{@code
 * @link @ConfigurationProperties(prefix = "person")
 * @link @YmlPropertySource(value = {"classpath:/hello.yml"}, name = "hello")
 * @link @Data
 * public class PersonProperties {
 *
 * private String name;
 *
 * private Integer age;
 *
 * private String school;
 * }}</pre>
 *
 * @author liuenyuan
 * @date 2019/6/16 20:13
 * @describe
 * @see YmlPropertySource
 * @see InstantiationAwareBeanPostProcessorAdapter
 * @see EnvironmentAware
 * @see ResourceLoaderAware
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class YmlPropertySourceAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements EnvironmentAware, ResourceLoaderAware {

    private Environment environment;

    private ResourceLoader resourceLoader;

    private final ConcurrentHashMap<String, List<Resource>> ymlPropertySourcesMap =
            new ConcurrentHashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment, "environment must be instance of ConfigurableEnvironment.");
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        YmlPropertySource ymlPropertySource = AnnotationUtils.findAnnotation(bean.getClass(), YmlPropertySource.class);
        if (ymlPropertySource != null) {
            String[] value = ymlPropertySource.value();
            String name = ymlPropertySource.name();
            List<Resource> resources = new ArrayList<>();
            Arrays.stream(value).forEach(location -> {
                Resource resource = resourceLoader.getResource(location);
                try {
                    if (resource.getInputStream() != null) {
                        resources.add(resource);
                    }
                } catch (IOException e) {
                    if (log.isWarnEnabled()) {
                        log.warn("file {} not found.", location);
                    }
                }
            });
            ymlPropertySourcesMap.put(name, resources);
        }
        if (!ymlPropertySourcesMap.isEmpty()) {
            ymlPropertySourcesMap.forEach((name, resources) -> {
                YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
                yamlPropertiesFactoryBean.setResources(resources.toArray(new Resource[resources.size()]));
                PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource(name, yamlPropertiesFactoryBean.getObject());
                ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
                MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
                propertySources.addFirst(propertiesPropertySource);
            });
        }
        return true;
    }
}
