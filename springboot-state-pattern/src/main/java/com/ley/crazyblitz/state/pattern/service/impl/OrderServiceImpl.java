package com.ley.crazyblitz.state.pattern.service.impl;

import com.ley.crazyblitz.state.pattern.bean.Order;
import com.ley.crazyblitz.state.pattern.handler.context.OrderHandlerContext;
import com.ley.crazyblitz.state.pattern.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderHandlerContext orderHandlerContext;

    @Override
    public String handleOrder(Order order) {
        return orderHandlerContext.getInstance(order.getType()).handle(order);
    }
}
