package com.ley.springboot.rabbitmq.producer;

import com.ley.springboot.rabbitmq.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String content) {
        this.rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE, TopicRabbitConfig.TOPIC_ROUTE_KEY, content);
    }
}
