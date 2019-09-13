package com.ley.crazyblitz.state.pattern.handler.impl;

import com.ley.crazyblitz.state.pattern.annotation.OrderHandlerType;
import com.ley.crazyblitz.state.pattern.bean.Order;
import com.ley.crazyblitz.state.pattern.handler.OrderHandler;
import org.springframework.stereotype.Component;

@Component
@OrderHandlerType("2")
public class GroupOrderHandler implements OrderHandler {
    @Override
    public String handle(Order order) {
        return "处理团购订单";
    }
}
