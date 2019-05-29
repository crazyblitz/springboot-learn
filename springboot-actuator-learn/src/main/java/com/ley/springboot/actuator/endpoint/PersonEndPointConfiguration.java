package com.ley.springboot.actuator.endpoint;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/19 9:59
 * @describe
 */
@Configuration
@ConditionalOnProperty(prefix = PersonEndpoint.PERSON_PREFIX, name = "enabled", matchIfMissing = true)
public class PersonEndPointConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public PersonEndpoint personEndpoint() {
        return new PersonEndpoint();
    }
}
