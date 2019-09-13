package com.ley.crazyblitz.state.pattern.controller;

import com.ley.crazyblitz.state.pattern.bean.Order;
import com.ley.crazyblitz.state.pattern.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/type")
    public String handleOrder(Order order) {
        return orderService.handleOrder(order);
    }

}
