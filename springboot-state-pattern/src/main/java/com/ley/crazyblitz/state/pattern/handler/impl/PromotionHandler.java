package com.ley.crazyblitz.state.pattern.handler.impl;

import com.ley.crazyblitz.state.pattern.annotation.OrderHandlerType;
import com.ley.crazyblitz.state.pattern.bean.Order;
import com.ley.crazyblitz.state.pattern.handler.OrderHandler;
import org.springframework.stereotype.Component;

@Component
@OrderHandlerType("3")
public class PromotionHandler implements OrderHandler {
    @Override
    public String handle(Order order) {
        return "处理促销订单";
    }
}
