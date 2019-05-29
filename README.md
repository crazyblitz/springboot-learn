#SpringBoot系列学习

spring boot版本：2.0.8.RELEASE

###模块说明

####1：first-spring-boot-starter和formatter-spring-boot-starter

(1):探究spring boot自动配置原理。

(2):探究spring探究条件化实现。

相关类：

- ConditionalOnClass
- OnClassCondition

(3):探究组合注解和注解属性抽象以及属性别名(@AliasFor)

相关类：

- AnnotationAttributeApplication：探究spring注解属性抽象AnnotationAttributes
- AnnotationAttributesApplication：使用AnnotatedElementUtils类获取注解归并后属性s
- ComposeAnnotationApplication：使用注解元信息抽象AnnotationMetadata获取注解元注解。
- TransactionServiceApplication：探究spring属性别名注解(@AliasFor)作用及使用注意问题。

####2：mybatis-dynamic-datasource

实现mybatis的动态数据源切换

相关类:

- com.gitee.ley.mybatis.aspect.DataSourceSwitchAspect:实现数据源动态切换的切面
- com.gitee.ley.mybatis.config.DruidDataSourceConfig:实现数据源的注入
- com.gitee.ley.mybatis.config.MybatisConfig: 实现mybatis的相关配置
- com.gitee.ley.mybatis.utils.DbContextHolder: 用于保存当前线程的数据源名称
- com.gitee.ley.mybatis.utils.DBTypeEnum: 数据源名称枚举,尽量是数据库名称

####3：spring-boot-kafka-learn

实现springboot初步整合kafka

（1)：出现kafka客户端连接不上远程腾讯云kafka(2.11-2.2.0)问题解决：

    # The address the socket server listens on. It will get the value returned from 
    # java.net.InetAddress.getCanonicalHostName() if not configured.
    #   FORMAT:
    #     listeners = listener_name://host_name:port
    #   EXAMPLE:
    #     listeners = PLAINTEXT://your.host.name:9092
    #listeners=PLAINTEXT://:9092
    #配置腾讯云内网地址
    listeners=PLAINTEXT://172.21.0.10:9092
    # Hostname and port the broker will advertise to producers and consumers. If not set, 
    # it usess the value for "listeners" if configured.  Otherwise, it will use the value
    # returned from java.net.InetAddress.getCanonicalHostName().
    #advertised.listeners=PLAINTEXT://your.host.name:9092
    #配置腾讯云外网地址
    advertised.listeners=PLAINTEXT://139.199.133.171:9092

(2)：springboot整合kafka客户端版本最好和安装的kafka版本保持一致

####4：springboot-actuator-learn

springboot实现actuator扩展

端点扩展：

相关类：

- PersonEndpoint
- PersonEndPointConguration

####5：springboot-auto-web-jar和spring-boot-auto-web

探究Spring web自动装配：使用了ServletContainerInitializer SPI机制，可以

在Tomcat启动的时候，加载自定义的servlet,filter,listener等组件。

####6：springboot-commons

spring boot公共工具类。有aop,io,net,reflect,security,tree,utils,web模块。

####7：springboot-embeded-tomcat

####探索spring实现Tomcat web自动装配的原理。

相关类：

- SpringServletContainerInitializer：扩展了ServletContainerInitializer
  - AbstractContextLoaderInitializer：注册了ContextLoader监听器
    - AbstractDispatcherServletInitializer：注册了DispatcherServlet
      - AbstractAnnotationConfigDispatcherServletInitializer：提供注解驱动。

AbstractAnnotationConfigDispatcherServletInitializer：最常用，一般下面两个方法。

    	/**
    	 * Specify {@code @Configuration} and/or {@code @Component} classes for the
    	 * {@linkplain #createRootApplicationContext() root application context}.
    	 * @return the configuration for the root application context, or {@code null}
    	 * if creation and registration of a root context is not desired
    	 */
    	@Nullable
    	protected abstract Class<?>[] getRootConfigClasses();
    
    	/**
    	 * Specify {@code @Configuration} and/or {@code @Component} classes for the
    	 * {@linkplain #createServletApplicationContext() Servlet application context}.
    	 * @return the configuration for the Servlet application context, or
    	 * {@code null} if all configuration is specified through root config classes.
    	 */
    	@Nullable
    	protected abstract Class<?>[] getServletConfigClasses();

####8：springboot-jpa

springboot初步整合jpa学习

####9：springboot-mongdb

springboot初步整合mongodb,实现文件上传与下载。

####10：springboot-mybatisplus

springboot整合mybatis-plus。

相关类：

- CodeGenerator：根据mybatis-plus提供的模板生成代码。

####11：springboot-rabbitmq

springboot初步整合rabbitmq

相关类：

- DirectRabbitConfig：实现点对点模式
- TopicRabbitConfig：实现发布订阅模式
- FanoutRabbitConfig：实现广播模式

####12：springboot-redis

springboot初步整合redis

整合过程中遇到问题：存储到redis nosql中，出现二进制情况。建议使用JSON存储。

版本问题：springboot1.5.x和springboot2.0.x由于版本API升级问题需要解决。

相关类：

- RedisConfig

####13：springboot-web

springboot对web相应数据进行封装

####14：spring-customized-annotation(重要)

探究spring的@Enable注解驱动模块设计原理和如何使用ClassPathBeanDefinitionScanner类

以及@Component注解多层次派生性

(1):@Enable注解驱动模块设计原理

相关包:com.ley.spring.customized.annotation.selector

原理涉及：

ConfigurationClassPostProcessor负责筛选@Component Class,@Configuration Class及@Bean方法,ConfigurationClassParse则从候选的Bean定义解析出ConfigurationClass集合,随后被ConfigurationClassBeanDefinitionReader转化并注册BeanDefinition。

(2):如何使用ClassPathBeanDefinitionScanner批量注册Bean

相关包:com.ley.spring.customized.annotation.repository

####15：spring-learn(重要)

探究Bean的生命周期以及协议扩展以及自定义@Autowire类型注解扩展

探究Bean生命周期：

相关包:com.ley.spring.learn.bean

主要源码实现类：AbstractAutowireCapableBeanFactory

com.ley.spring.learn.protocol

相关包:com.ley.spring.learn.protocol

自定义@Autowire类型注解扩展

相关包:com.ley.spring.learn.inject

可以参照AutowiredAnnotationBeanPostProcessor实现。

AnnotationConfigUtils.registerAnnotationConfigProcessors(BeanDefinitionRegistry registry)注册了

spring框架默认的注解后置处理器。






