package com.ley.spring.customized.annotation.enable.module;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportSelectorRegistrar.class)
public @interface EnableServerRegistrar {

    /**
     * server type
     **/
    Server.Type type() default Server.Type.HTTP;
}