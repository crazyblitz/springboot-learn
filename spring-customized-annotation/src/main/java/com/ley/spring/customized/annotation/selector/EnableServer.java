package com.ley.spring.customized.annotation.selector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:22
 * @describe
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportSelector.class)
//@Import(ServerImportSelectorRegistrar.class)
public @interface EnableServer {

    /**
     * server type
     **/
    Server.Type type() default Server.Type.HTTP;
}
