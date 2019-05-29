package com.ley.springboot.autoconfig;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "server.info")
@Data
@ToString(exclude = {"environment"})
public class ServerInfoProperties implements EnvironmentAware {

    private MetaInfo metaInfo = new MetaInfo();

    private Integer port = 0;

    private String address;

    private Environment environment;

    @Data
    public static class MetaInfo {

        private String[] alias;

        private String company;

        private String group;

        private String area;

        private Integer serverCount = 1;
    }

    @PostConstruct
    protected void afterPropertiesSet() {
        if (port == null || port == 0) {
            port = Integer.parseInt(environment.getProperty("server.port"));
        }

        if (!StringUtils.hasText(address)) {
            address = environment.getProperty("server.address");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}