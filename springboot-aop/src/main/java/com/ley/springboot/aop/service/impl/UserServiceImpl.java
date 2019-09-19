package com.ley.springboot.aop.service.impl;

import com.google.common.collect.ImmutableList;
import com.ley.springboot.aop.bean.User;
import com.ley.springboot.aop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> users = ImmutableList.of(new User(UUID.randomUUID().toString(), "刘恩源"),
            new User(UUID.randomUUID().toString(), "刘恩源1"), new User(UUID.randomUUID().toString(), "刘恩源2"));

    @Override
    public List<User> listUsers() {
        return users;
    }
}
