package com.ley.crazyblitz.state.pattern.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private String id;
    private String type;
    private BigDecimal price;
}
