package com.ley.springboot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        log.info("request uri: {},port: {}",request.getRequestURI(),request.getServerPort());
        return "index";
    }
}
