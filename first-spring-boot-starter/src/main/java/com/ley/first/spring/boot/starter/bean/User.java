package com.ley.first.spring.boot.starter.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:38
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;
}
