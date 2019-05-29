package com.ley.springboot.aop.controller;

import com.ley.springboot.aop.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(UUID.randomUUID().toString(), "刘恩源"));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源1"));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源2"));

    }

    @GetMapping("/")
    public List<User> getUserByUserName() {
        return users;
    }


    public static List<User> getUsers() {
        return users;
    }
}
