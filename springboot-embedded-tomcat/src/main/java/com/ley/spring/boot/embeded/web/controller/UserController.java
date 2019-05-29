package com.ley.spring.boot.embeded.web.controller;

import com.ley.spring.boot.embeded.root.bean.User;
import com.ley.spring.boot.embeded.root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 21:03
 * @describe
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> listUserName() {
        return userService.getUsers();
    }
}
