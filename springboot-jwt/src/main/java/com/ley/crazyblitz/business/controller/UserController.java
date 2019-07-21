package com.ley.crazyblitz.business.controller;


import com.ley.crazyblitz.business.entity.User;
import com.ley.crazyblitz.business.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhiyuan
 * @since 2019-07-21
 */
@RestController
@RequestMapping("/api/business/users")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("")
    public List<User> listAllUsers() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userService.list();
    }
}
