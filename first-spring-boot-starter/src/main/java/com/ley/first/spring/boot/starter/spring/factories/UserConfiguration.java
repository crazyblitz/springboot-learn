package com.ley.first.spring.boot.starter.spring.factories;

import com.ley.first.spring.boot.starter.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/29 23:19
 * @describe
 */
@Configuration
@ConditionalOnClass(value = {User.class})
public class UserConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public User user() {
        return new User(UUID.randomUUID().toString(), "刘恩源");
    }
}
