package com.ley.springboot.jpa;

import com.ley.springboot.jpa.dao.UserDao;
import com.ley.springboot.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JpaApplication.class})
public class JpaTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testJpa() {
        User user = new User();
        user.setAge("20");
        user.setName("刘恩源");
        user.setAddress("河南省信阳市商城县苏仙石乡");
        userDao.save(user);

        System.out.println(userDao.findAll());
    }
}
