package com.crazyblitz.spring.boot.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/6/16 11:58
 * @describe
 */
@Component
@Data
public class HelloUser {

    @Value("${hello.user.name:default}")
    private String userName;

    /**
     * 注入操作系统属性
     **/
    @Value("#{systemProperties['os.name']}")
    private String systemPropertiesName;
}
