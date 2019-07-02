package com.ley.spring.learn.aspect;

import java.util.Arrays;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public List<String> listPersonNames() {
        return Arrays.asList("刘恩源","寇奇");
    }
}
