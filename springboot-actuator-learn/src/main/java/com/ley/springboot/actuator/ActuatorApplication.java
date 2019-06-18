package com.ley.springboot.actuator;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

/**
 * 类描述: spring boot actuator learn
 *
 * @author liuenyuan
 * @date 2019/5/19 9:55
 * @describe
 * @see org.springframework.boot.actuate.endpoint.annotation.WriteOperation
 * @see org.springframework.boot.actuate.endpoint.annotation.ReadOperation
 * @see org.springframework.boot.actuate.endpoint.annotation.Endpoint
 */
@SpringBootApplication
public class ActuatorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ActuatorApplication.class);
    }

    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args -> System.out.println(String.format("当前的WebServer实现类为: %s", context
                .getWebServer().getClass().getName()));
    }


    @EventListener({WebServerInitializedEvent.class})
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println(String.format("当前的WebServer实现类为: %s", event
                .getWebServer().getClass().getName()));
    }
}
