package com.ley.spring.customized.annotation.registrara;

import com.ley.spring.customized.annotation.selector.EnableServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/7 13:12
 * @describe
 */
@EnableServer
@SpringBootApplication
public class ImportRegistrar {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ImportRegistrar.class);
        builder.web(WebApplicationType.NONE).run(args);
    }
}
