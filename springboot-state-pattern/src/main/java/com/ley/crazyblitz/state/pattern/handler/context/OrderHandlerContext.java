package com.ley.crazyblitz.state.pattern.handler.context;


import com.alibaba.fastjson.JSONObject;
import com.ley.crazyblitz.state.pattern.annotation.OrderHandlerType;
import com.ley.crazyblitz.state.pattern.handler.OrderHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OrderHandlerContext implements SmartInitializingSingleton, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<String, Class<?>> orderHandlerMap = new HashMap<>(8);

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, OrderHandler> orderHandlers = applicationContext.getBeansOfType(OrderHandler.class);
        if (log.isInfoEnabled()) {
            log.info("orderHandlers: {}", orderHandlers);
        }
        if (!CollectionUtils.isEmpty(orderHandlers)) {
            orderHandlers.forEach((key, value) -> {
                OrderHandlerType orderHandlerType = AnnotationUtils.findAnnotation(value.getClass(), OrderHandlerType.class);
                if (orderHandlerType != null) {
                    if (!orderHandlerMap.containsKey(orderHandlerType.value())) {
                        orderHandlerMap.put(orderHandlerType.value(), value.getClass());
                    } else {
                        throw new IllegalArgumentException(String.format("OrderHandlerContext.orderHandlerMap already constains " +
                                "order type %s,please check your logic.", orderHandlerType.value()));
                    }
                }
            });
            if (log.isInfoEnabled()) {
                log.info("orderHandlerMap: {}", JSONObject.toJSONString(orderHandlerMap));
            }
        } else {
            log.warn("not found order handler bean in beanFactory");
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public OrderHandler getInstance(String type) {
        Class orderHandlerClass = orderHandlerMap.get(type);
        if (orderHandlerClass == null) {
            throw new IllegalArgumentException("not found order handler for type: " + type);
        } else {
            return (OrderHandler) applicationContext.getBean(orderHandlerMap.get(type));
        }
    }
}
