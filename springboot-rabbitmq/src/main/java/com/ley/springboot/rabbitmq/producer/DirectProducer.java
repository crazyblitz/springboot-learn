package com.ley.springboot.rabbitmq.producer;

import com.ley.springboot.rabbitmq.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class DirectProducer implements RabbitTemplate.ConfirmCallback {

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public DirectProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }


    public void sendMessage(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入DIRECT_ROUTE_KEY对应的队列当中去,对应的是队列DIRECT_EXCHANGE
        rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_EXCHANGE, DirectRabbitConfig.DIRECT_ROUTE_KEY, content, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(" 回调id:" + correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + cause);
        }
    }
}
