package com.ley.spring.boot.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/16 13:41
 * @describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String userName;
    private String userId;
    private String state;

}
