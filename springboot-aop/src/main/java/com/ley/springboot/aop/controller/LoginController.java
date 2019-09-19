package com.ley.springboot.aop.controller;

import com.ley.springboot.aop.annotation.NeedLogin;
import com.ley.springboot.aop.bean.User;
import com.ley.springboot.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @NeedLogin(value = false)
    public String login(String userName, HttpServletRequest request) {
        boolean find = userService.listUsers().stream().map(User::getUserName).collect(Collectors.toList()).contains(userName);
        if (find) {
            request.getSession().setAttribute("userName", userName);
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}
