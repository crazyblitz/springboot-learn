package com.ley.spring.boot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/16 13:40
 * @describe
 */
@SpringBootApplication
public class KafkaApplication {

    @Autowired
    private KafkaProducer kafkaSender;

    private static final int MESSAGE_SEND_COUNT = 10;

    @PostConstruct
    public void init() {
        for (int i = 0; i < MESSAGE_SEND_COUNT; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.sendLog(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
