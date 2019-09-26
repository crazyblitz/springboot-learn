package com.ley.springboot.aop.controller;

import com.google.gson.Gson;
import com.ley.springboot.aop.annotation.NeedLogin;
import com.ley.springboot.aop.bean.User;
import com.ley.springboot.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;

    @GetMapping("")
    @NeedLogin
    public String getUserByUserName() {
        return gson.toJson(userService.listUsers());
    }
}
