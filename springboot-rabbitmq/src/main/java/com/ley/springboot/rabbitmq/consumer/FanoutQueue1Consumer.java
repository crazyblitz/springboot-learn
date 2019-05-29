package com.ley.springboot.rabbitmq.consumer;

import com.ley.springboot.rabbitmq.config.FanoutRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = {FanoutRabbitConfig.FANOUT_QUEUE1})
public class FanoutQueue1Consumer {

    @RabbitHandler
    public void process(String message) {
        log.info("Fanout Receiver1: {} " + message);
    }
}
