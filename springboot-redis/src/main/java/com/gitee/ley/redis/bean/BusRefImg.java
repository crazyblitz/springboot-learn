package com.gitee.ley.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRefImg implements Serializable{


    private static final long serialVersionUID = -87899399238512643L;
    private String id;
    private String busId;
    private String imgId;

}
