package com.crazyblitz.spring.boot.config.conversion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * order
 *
 * @author liuenyuan
 **/
@Data
public class Order {

    private String orderId;

    @JsonFormat(pattern = "yyyy-MM:dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;

    private String[] orderGoods;
}
