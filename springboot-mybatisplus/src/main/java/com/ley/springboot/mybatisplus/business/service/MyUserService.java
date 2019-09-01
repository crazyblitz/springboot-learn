package com.ley.springboot.mybatisplus.business.service;

import com.ley.springboot.mybatisplus.business.entity.User;

public interface MyUserService {

    void insertSelective(User user);
}
