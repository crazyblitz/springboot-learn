package com.ley.first.spring.boot.starter.condition;

import com.ley.first.spring.boot.starter.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/27 20:44
 * @describe
 */
@SpringBootApplication
@Slf4j
public class ConditionalApplication {

    @ConditionalOnClass(name = {"com.ley.first.spring.boot.starter.condition.Person"})
    @Bean
    public Person person() {
        return new Person(20, "刘恩源");
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConditionalApplication.class, args);
        System.out.println(AutoConfigurationPackages.get(ctx.getBeanFactory()));
        System.out.println(ctx.containsBean("person"));
        System.out.println(ctx.getBean("person"));
        ctx.close();
    }
}
