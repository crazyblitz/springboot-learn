package com.ley.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题模式(Topic)<br/>
 * topic exchange主要根据通配符来转发消息.<br/>
 * 路由键必须是一串字符,用句号(.)隔开,比如说agreements.us,或者agreements.eu.stockholm 等<br/>
 * 路由模式必须包含一个星号(*),主要用于匹配路由键指定位置的一个单词
 **/
@Configuration
public class TopicRabbitConfig {


    public static final String TOPIC_QUEUE_1 = "topic.queue1";

    public static final String TOPIC_QUEUE_2 = "topic.queue2";

    public static final String TOPIC_EXCHANGE = "topic-exchange";

    public static final String TOPIC_ROUTE_KEY = "topic.#";

    @Bean(value = "topicQueue1")
    public Queue topicQueue1() {
        return new Queue(TopicRabbitConfig.TOPIC_QUEUE_1, false);
    }

    @Bean(value = "topicQueue2")
    public Queue topicQueue2() {
        return new Queue(TopicRabbitConfig.TOPIC_QUEUE_2, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TopicRabbitConfig.TOPIC_EXCHANGE);
    }


    @Bean
    Binding bindingTopicQueue1(@Qualifier("topicQueue1") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TopicRabbitConfig.TOPIC_ROUTE_KEY);
    }

    @Bean
    Binding bindingTopicQueue2(@Qualifier("topicQueue2") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TopicRabbitConfig.TOPIC_ROUTE_KEY);//*表示一个词,#表示零个或多个词
    }
}
