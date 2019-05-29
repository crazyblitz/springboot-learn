package com.ley.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广播模式(发布-订阅模式)
 **/
@Configuration
public class FanoutRabbitConfig {

    public static final String FANOUT_QUEUE1 = "fanout.queue1";

    public static final String FANOUT_QUEUE2 = "fanout.queue2";

    public static final String FANOUT_EXCHANGE = "fanout-exchange";


    @Bean(value = "fanoutQueue1")
    public Queue fanoutQueue1() {
        return new Queue(FanoutRabbitConfig.FANOUT_QUEUE1, false);
    }

    @Bean(value = "fanoutQueue2")
    public Queue fanoutQueue2() {
        return new Queue(FanoutRabbitConfig.FANOUT_QUEUE2, false);
    }

    @Bean(value = "fanoutExchange")
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FanoutRabbitConfig.FANOUT_EXCHANGE);
    }


    @Bean
    Binding bindingQueue1(@Qualifier("fanoutQueue1") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding bindingQueue2(@Qualifier("fanoutQueue2") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
