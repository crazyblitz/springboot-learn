package com.ley.spring.customized.annotation.enable.module;

import com.ley.spring.customized.annotation.enable.module.Server;
import com.ley.spring.customized.annotation.enable.module.ServerImportSelector;
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
public @interface EnableServerSelector {

    /**
     * server type
     **/
    Server.Type type() default Server.Type.HTTP;
}
