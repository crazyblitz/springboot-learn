package com.ley.crazyblitz.business.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private String id;
    private String pwd;
    private Date lastPasswordResetDate;
}
