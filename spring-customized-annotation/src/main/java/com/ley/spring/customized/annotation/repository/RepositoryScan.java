package com.ley.spring.customized.annotation.repository;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * MyRepository scan
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RepositoryScannerRegistrar.class)
public @interface RepositoryScan {

    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise
     * config declarations e.g.
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for MyBatis interfaces. Note that only interfaces
     * with at least one method will be registered; concrete classes will be
     * ignored.
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages()} for specifying the packages
     * to scan for annotated components. The package of each class specified will be scanned.
     * <p>Consider creating a special no-op marker class or interface in each package
     * that serves no purpose other than being referenced by this attribute.
     */
    Class<?>[] basePackageClasses() default {};


    /**
     * This property specifies the config that the scanner will search for.
     * <p>
     * The scanner will register all interfaces in the base package that also have
     * the specified config.
     * <p>
     * Note this can be combined with markerInterface.
     */
    Class<? extends Annotation> annotationClass() default Annotation.class;

}
