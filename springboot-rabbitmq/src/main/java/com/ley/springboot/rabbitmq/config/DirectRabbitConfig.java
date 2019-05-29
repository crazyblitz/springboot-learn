package com.ley.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直接模式(direct)<br/>
 * 消费者和生产者关系:一对一
 **/
@Configuration
public class DirectRabbitConfig {

    public static final String DIRECT_QUEUE = "direct-queue";

    public static final String DIRECT_EXCHANGE = "direct-exchange";

    public static final String DIRECT_ROUTE_KEY = "direct-route-key";

    /**
     * 创建消息队列
     **/
    @Bean(name = DirectRabbitConfig.DIRECT_QUEUE)
    public Queue directQueue() {
        return new Queue(DirectRabbitConfig.DIRECT_QUEUE, true);//队列持久
    }

    /**
     * 创建消息交换机
     **/
    @Bean(name = DirectRabbitConfig.DIRECT_EXCHANGE)
    public DirectExchange directChange() {
        return new DirectExchange(DirectRabbitConfig.DIRECT_EXCHANGE);
    }


    /**
     * 创建绑定器
     **/
    @Bean
    public Binding bingDirectQueue(@Qualifier(DirectRabbitConfig.DIRECT_QUEUE) Queue queue, @Qualifier(DirectRabbitConfig.DIRECT_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DirectRabbitConfig.DIRECT_ROUTE_KEY);
    }

}
