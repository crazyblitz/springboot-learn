package com.ley.spring.customized.annotation.aware;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static com.ley.spring.customized.annotation.aware.FirstApplicationContextInitializer.JDBC_PROPERTY_SOURCE_NAME;

@SpringBootApplication
public class ApplicationContextInitializerApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ApplicationContextInitializerApplication.class);
        ConfigurableApplicationContext ctx = builder.web(WebApplicationType.NONE).run(args);
        System.out.println("application id: " + ctx.getId());
        System.out.println(ctx.getEnvironment().getPropertySources());
        System.out.println(ctx.getEnvironment().getPropertySources().get(JDBC_PROPERTY_SOURCE_NAME).getSource());
        ctx.close();
    }
}
