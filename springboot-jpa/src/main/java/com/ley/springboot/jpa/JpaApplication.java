package com.ley.springboot.jpa;

import com.ley.springboot.jpa.dao.UserDao;
import com.ley.springboot.jpa.entity.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JpaApplication {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HikariDataSource hikariDataSource;

    @PostConstruct
    public void testJpa() {
        User user = new User();
        user.setAge("21");
        user.setName("王丹丹");
        user.setAddress("河南省信阳市商城县苏仙石乡");
        userDao.save(user);
        System.out.println(userDao.count());

        System.out.println(hikariDataSource.toString());
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(JpaApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(args);
        context.close();
    }
}
