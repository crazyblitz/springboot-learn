package com.gitee.ley.mybatis.service;


import com.gitee.ley.mybatis.entity.User;

public interface UserService {

    User findOne(String userId);
}
