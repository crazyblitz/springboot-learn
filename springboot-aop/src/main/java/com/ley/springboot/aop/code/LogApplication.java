package com.ley.springboot.aop.code;

import com.ley.springboot.aop.code.config.LogAspectConfig;
import com.ley.springboot.aop.code.service.CodeService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LogApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LogAspectConfig.class);
        CodeService codeService = ctx.getBean(CodeService.class);
        codeService.add(1, 2);
        System.out.println(ctx.getBean(LogAspectConfig.class));
        ctx.close();
    }

}
