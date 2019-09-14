package com.ley.crazyblitz.state.pattern.handler.impl;

import com.ley.crazyblitz.state.pattern.annotation.OrderHandlerType;
import com.ley.crazyblitz.state.pattern.bean.Order;
import com.ley.crazyblitz.state.pattern.handler.OrderHandler;
import org.springframework.stereotype.Component;

@Component
@OrderHandlerType("4")
public class NormalOrderHandler implements OrderHandler {
    @Override
    public String handle(Order order) {
        return "处理普通订单";
    }
}
