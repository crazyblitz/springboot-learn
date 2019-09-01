package com.ley.crazyblitz.acm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${user.name:default}")
    private String name;

    @GetMapping("/acm")
    public String getUserName(){
        return name;
    }
}
