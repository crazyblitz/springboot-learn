package com.ley.spring.learn.inject.service;


import com.ley.spring.learn.inject.annotation.MyAutowire;
import com.ley.spring.learn.inject.annotation.MyInject;
import com.ley.spring.learn.inject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //@MyAutowire
    //@Autowired
    @MyInject
    private User user;

    public User getUser() {
        return user;
    }
}
