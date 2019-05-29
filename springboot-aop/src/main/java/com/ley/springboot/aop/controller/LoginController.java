package com.ley.springboot.aop.controller;

import com.ley.springboot.aop.annotation.NeedLogin;
import com.ley.springboot.aop.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/user")
    @NeedLogin
    public String login(String userName, HttpSession session) {
        List<User> users = UserController.getUsers();
        User findUser = new User();
        findUser.setUserName(userName);
        boolean find = users.contains(findUser);
        if (find) {
            session.setAttribute("userName", userName);
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}
