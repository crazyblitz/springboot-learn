package com.crazyblitz.spring.boot.config.annotation;


import com.crazyblitz.spring.boot.config.annotation.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.lang.annotation.*;

/**
 * yaml property source and extension {@link PropertySource}
 *
 * @author liuenyuan
 * @see org.springframework.context.annotation.PropertySource
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(YamlPropertySources.class)
public @interface YamlPropertySource {


    /**
     * Indicate the name of this property source. If omitted, a name will
     * be generated based on the description of the underlying resource.
     *
     * @see org.springframework.core.env.PropertySource#getName()
     * @see org.springframework.core.io.Resource#getDescription()
     */
    String name() default "";

    /**
     * Indicate the resource location(s) of the properties file to be loaded.
     * <p>Both traditional and XML-based properties file formats are supported
     * &mdash; for example, {@code "classpath:/com/myco/app.properties"}
     * or {@code "file:/path/to/file.xml"}.
     * <p>Resource location wildcards (e.g. *&#42;/*.properties) are not permitted;
     * each location must evaluate to exactly one {@code .properties} resource.
     * <p>${...} placeholders will be resolved against any/all property sources already
     * registered with the {@code Environment}. See {@linkplain PropertySource above}
     * for examples.
     * <p>Each location will be added to the enclosing {@code Environment} as its own
     * property source, and in the order declared.
     */
    String[] value();

    /**
     * Indicate if failure to find the a {@link #value() property resource} should be
     * ignored.
     * <p>{@code true} is appropriate if the properties file is completely optional.
     * Default is {@code false}.
     *
     * @since 4.0
     */
    boolean ignoreResourceNotFound() default false;

    /**
     * A specific character encoding for the given resources, e.g. "UTF-8".
     *
     * @since 4.3
     */
    String encoding() default "";

    /**
     * Specify a custom {@link PropertySourceFactory}, if any.
     * <p>By default, a default factory for standard resource files will be used.
     *
     * @see org.springframework.core.io.support.DefaultPropertySourceFactory
     * @see org.springframework.core.io.support.ResourcePropertySource
     * @since 4.3
     */
    Class<? extends PropertySourceFactory> factory() default YamlPropertySourceFactory.class;
}
