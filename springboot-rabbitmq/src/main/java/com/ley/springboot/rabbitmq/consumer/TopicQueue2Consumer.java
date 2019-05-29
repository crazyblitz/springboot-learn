package com.ley.springboot.rabbitmq.consumer;

import com.ley.springboot.rabbitmq.config.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = {TopicRabbitConfig.TOPIC_QUEUE_2})
public class TopicQueue2Consumer {

    @RabbitHandler
    public void process(String message) {
        log.info("Topic Receiver2 {} " + message);
    }
}
