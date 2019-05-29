package com.ley.springboot.rabbitmq.consumer;

import com.ley.springboot.rabbitmq.config.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = {TopicRabbitConfig.TOPIC_QUEUE_1})
public class TopicQueue1Consumer {

    @RabbitHandler
    public void process(String message) {
        log.info("Topic Receiver1 {} " + message);
    }
}
