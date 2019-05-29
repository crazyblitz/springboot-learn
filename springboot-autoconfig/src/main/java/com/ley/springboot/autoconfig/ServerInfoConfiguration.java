package com.ley.springboot.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

@Configuration
@EnableConfigurationProperties(ServerInfoProperties.class)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@Slf4j
public class ServerInfoConfiguration {

    private final ServerInfoProperties serverInfoProperties;


    public ServerInfoConfiguration(ServerInfoProperties serverInfoProperties) {
        this.serverInfoProperties = serverInfoProperties;
        log.info("serverInfoProperties: {}", serverInfoProperties);
    }


    @ConditionalOnMissingBean
    @Bean
    public Server server() {
        Server server = new Server();
        server.setServerInfoProperties(serverInfoProperties);
        return server;
    }
}
