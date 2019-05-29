package com.ley.springboot.autoconfig.bean.life;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope
public class User {

    private String name;

    private String age;


}
