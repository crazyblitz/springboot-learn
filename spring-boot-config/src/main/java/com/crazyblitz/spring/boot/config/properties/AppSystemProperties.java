package com.crazyblitz.spring.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/6/17 17:36
 * @describe
 * @see java.time.Duration
 * @see org.springframework.boot.convert.DurationUnit
 * @see ChronoUnit
 */
@ConfigurationProperties(prefix = "app.system")
@Data
public class AppSystemProperties {

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration readTimeout = Duration.ofSeconds(5);
}
