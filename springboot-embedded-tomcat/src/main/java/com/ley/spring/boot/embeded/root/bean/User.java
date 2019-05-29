package com.ley.spring.boot.embeded.root.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 23:06
 * @describe
 */
public class User {

    private String name;

    private Integer age;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastDate;

    public User() {
    }

    public User(String name, Integer age, Date lastDate) {
        this.name = name;
        this.age = age;
        this.lastDate = lastDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
