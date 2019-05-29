package com.ley.spring.customized.annotation;

import com.ley.spring.customized.annotation.repository.RepositoryScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;



/**
 * {@link org.springframework.core.env.PropertySource}常用子类
 * <br/>
 * {@link org.springframework.core.env.MapPropertySource}
 * <br/>
 * {@link org.springframework.core.env.PropertiesPropertySource}
 * <br/>
 * {@link org.springframework.core.io.support.ResourcePropertySource}
 * <br/>
 * {@link MutablePropertySources }
 *
 * @author liuenyuan
 * @see PropertySourcesPropertyResolver
 * @see SpringApplicationJsonEnvironmentPostProcessor
 **/
@SpringBootApplication
@RepositoryScan
@Slf4j
public class CustomizedAnnotationApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(CustomizedAnnotationApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args).close();
    }


}
