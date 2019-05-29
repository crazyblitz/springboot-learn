package com.ley.springboot.rabbitmq.consumer;

import com.ley.springboot.rabbitmq.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE)
@Slf4j
public class DirectConsumer {

    @RabbitHandler
    public void receiveMessage(String content) {
        log.info("接收处理队列{}当中的消息: {}", DirectRabbitConfig.DIRECT_QUEUE, content);
    }
}
