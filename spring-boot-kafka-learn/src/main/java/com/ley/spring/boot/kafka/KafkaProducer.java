package com.ley.spring.boot.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/16 13:42
 * @describe
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送数据
     *
     * @param userId
     */
    public void sendLog(String userId) {
        User user = new User("刘恩源", userId, "1");
        log.info("发送用户数据:" + user);
        kafkaTemplate.send("user", JSON.toJSONString(user));
    }

}
