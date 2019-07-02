package com.ley.spring.learn.aspect;


import java.util.Arrays;
import java.util.List;

public class UserService {

    public List<String> listUserNames() {
        return Arrays.asList("刘恩源", "寇奇");
    }
}