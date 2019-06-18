package com.crazyblitz.spring.boot.config.annotation;



import java.lang.annotation.*;

/**
 * 类描述: load yml or yaml file into {@link org.springframework.core.env.Environment}
 *
 * @author liuenyuan
 * @date 2019/6/16 20:12
 * @describe
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YmlPropertySource {

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
     * &mdash; for example, {@code "classpath:/com/myco/app.yml|yaml"}
     * <p>Resource location wildcards (e.g. *&#42;/*.yml|yaml) are not permitted;
     * each location must evaluate to exactly one {@code .properties} resource.
     * <p>${...} placeholders will be resolved against any/all property sources already
     * registered with the {@code Environment}. See {@linkplain YmlPropertySource above}
     * for examples.
     * <p>Each location will be added to the enclosing {@code Environment} as its own
     * property source, and in the order declared.
     */
    String[] value();
}
