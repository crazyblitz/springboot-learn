package com.ley.spring.boot.embeded.root.service;

import com.ley.spring.boot.embeded.root.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 20:30
 * @describe
 */
@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    {
        users.add(new User("刘恩源", 20, new Date(System.currentTimeMillis())));
        users.add(new User("刘恩源1", 21, new Date(System.currentTimeMillis())));
        users.add(new User("刘恩源2", 22, new Date(System.currentTimeMillis())));
    }


    public List<User> getUsers() {
        return users;
    }
}
