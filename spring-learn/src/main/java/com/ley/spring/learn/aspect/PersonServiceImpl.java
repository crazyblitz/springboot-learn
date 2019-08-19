package com.ley.spring.learn.aspect;


import java.util.Arrays;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public List<String> listPersonNames() {
        hello();
        return Arrays.asList("刘恩源","寇奇");
    }

    protected void hello(){
        System.out.println("Hello World!");
    }
}
