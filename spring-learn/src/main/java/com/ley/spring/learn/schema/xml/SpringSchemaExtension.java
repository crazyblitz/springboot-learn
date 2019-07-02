package com.ley.spring.learn.schema.xml;

import com.ley.spring.learn.schema.xml.nest.Component;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

/**
 * spring schema扩展机制
 **/
public class SpringSchemaExtension {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myns.xml",
                "nest.xml");
        SimpleDateFormat dateFormat = context.getBean(SimpleDateFormat.class);
        System.out.println(dateFormat.format(System.currentTimeMillis()));
        Component component = context.getBean(Component.class);
        System.out.println(component);
        context.close();
    }
}
