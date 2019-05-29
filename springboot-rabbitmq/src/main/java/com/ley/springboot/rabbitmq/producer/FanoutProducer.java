package com.ley.springboot.rabbitmq.producer;

import com.ley.springboot.rabbitmq.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String content) {
        this.rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE, "", content);
    }
}
