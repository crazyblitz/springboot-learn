package com.ley.spring.customized.annotation.enable.module.registrar;

import com.ley.spring.customized.annotation.enable.module.EnableServerRegistrar;
import com.ley.spring.customized.annotation.enable.module.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/7 13:12
 * @describe
 */
@EnableServerRegistrar
@Configuration
public class ImportRegistrarApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ImportRegistrarApplication.class);
        //启动上下文
        ctx.refresh();
        Server server = ctx.getBean(Server.class);
        System.out.println(server);
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(String.format("beanName: %s", beanName));
        }
    }
}
