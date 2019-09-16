package com.ley.springboot.aop.advisor;


import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LogAdvisorApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LogAdvisorApplication.class);
        ConfigurableApplicationContext context = builder.web(WebApplicationType.NONE).run(args);
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.hello();
        System.out.println(context.getBeansOfType(AbstractAutoProxyCreator.class));
        context.close();
    }

}
