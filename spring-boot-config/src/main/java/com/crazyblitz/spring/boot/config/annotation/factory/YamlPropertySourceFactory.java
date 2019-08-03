package com.crazyblitz.spring.boot.config.annotation.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * extension {@link DefaultPropertySourceFactory},and this class can load yml or yaml configuration
 * file.
 *
 * @author liuenyuan
 * @see org.springframework.core.io.support.DefaultPropertySourceFactory
 * @see PropertySourceFactory
 **/
public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

    private final YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();

    private final static String YML_SUFFIX = "yml";

    private final static String YAML_SUFFIX = "yaml";

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        if (Objects.requireNonNull(encodedResource.getResource().getFilename()).contains(YML_SUFFIX)) {
            yamlPropertiesFactoryBean.setResources(encodedResource.getResource());
            Properties properties = yamlPropertiesFactoryBean.getObject();
            assert properties != null;
            return new PropertiesPropertySource(name, properties);
        } else if (encodedResource.getResource().getFilename().contains(YAML_SUFFIX)) {
            yamlPropertiesFactoryBean.setResources(encodedResource.getResource());
            Properties properties = yamlPropertiesFactoryBean.getObject();
            assert properties != null;
            return new PropertiesPropertySource(name, properties);
        } else {
            return super.createPropertySource(name, encodedResource);
        }
    }
}
