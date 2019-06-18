package com.ley.spring.learn.inject.service;


import com.ley.spring.learn.inject.annotation.MyAutowire;
import com.ley.spring.learn.inject.annotation.MyInject;
import com.ley.spring.learn.inject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @MyAutowire
    private User user;

    @MyInject
    @Qualifier("user")
    private User user1;

    public User getUser() {
        return user;
    }


    public User getUser1() {
        return user1;
    }
}
