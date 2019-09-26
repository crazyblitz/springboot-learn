package com.ley.springboot.aop.controller;

import com.ley.springboot.aop.annotation.NeedLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@NeedLogin(value = false)
public class IndexController {

    @GetMapping("/index")
    @NeedLogin(value = false)
    public String index() {
        return "index";
    }
}
