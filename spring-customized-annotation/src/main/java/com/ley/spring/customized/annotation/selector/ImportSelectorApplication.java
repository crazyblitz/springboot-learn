package com.ley.spring.customized.annotation.selector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/22 10:28
 * @describe
 */
@Configuration
@EnableServer
public class ImportSelectorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ImportSelectorApplication.class);
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
