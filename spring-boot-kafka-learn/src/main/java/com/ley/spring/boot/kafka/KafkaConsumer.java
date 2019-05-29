package com.ley.spring.boot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/16 13:45
 * @describe
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"user"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record =" + kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            log.info("消费消息:" + message);
        }
    }

}
