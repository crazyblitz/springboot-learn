package com.ley.springboot.rabbitmq;

import com.ley.springboot.rabbitmq.producer.DirectProducer;
import com.ley.springboot.rabbitmq.producer.FanoutProducer;
import com.ley.springboot.rabbitmq.producer.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitmqApplication.class})
public class RabbitmqTest {

    @Autowired
    private DirectProducer directProducer;

    @Autowired
    private TopicProducer topicProducer;

    @Autowired
    private FanoutProducer fanoutProducer;

    @Test
    public void testDirect() {
        directProducer.sendMessage("刘恩源");
    }

    @Test
    public void testTopic() {
        topicProducer.sendMessage("刘恩源");
    }

    @Test
    public void testFanout() {
        fanoutProducer.sendMessage("刘恩源");
    }
}
