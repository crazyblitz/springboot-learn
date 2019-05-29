package com.ley.springboot.actuator.tomcat;

import org.apache.catalina.valves.AccessLogValve;
import org.apache.catalina.valves.Constants;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 类描述: 定制内嵌的tomcat服务器
 *
 * @author liuenyuan
 * @date 2019/5/24 16:12
 * @describe
 */
@Configuration
public class TomcatConfiguration {

    @Bean
    public ConfigurableServletWebServerFactory embeddedServletContainerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        //设置端口
        factory.setPort(8089);

        //设置404错误界面
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/404.html");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.html");

        factory.addErrorPages(error401Page, error404Page, error500Page);

        //设置在容器初始化的时候触发
        factory.addInitializers((servletContext) -> {
            System.out.println(" = = = = 获取服务器信息 = = " + servletContext.getServerInfo());
        });
        //设置最大连接数和最大线程数
        factory.addConnectorCustomizers((connector) -> {
            Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
            protocolHandler.setMaxConnections(2000);
            protocolHandler.setMaxThreads(500);
        });
        //设置访问日志记录文件的目录
        factory.addContextValves(getLogAccessLogValue());
        return factory;
    }


    private AccessLogValve getLogAccessLogValue() {
        AccessLogValve accessLogValve = new AccessLogValve();
        accessLogValve.setDirectory("d:/tmp/logs");
        accessLogValve.setEnabled(true);
        accessLogValve.setPattern(Constants.AccessLog.COMMON_PATTERN);
        accessLogValve.setPrefix("SpringBoot-Access-Log");
        accessLogValve.setSuffix(".txt");
        return accessLogValve;
    }
}
