package com.gitee.ley.mybatis;

import com.gitee.ley.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisApplication.class})
@Slf4j
public class DynamicDatasourceTest {

    @Autowired
    @Qualifier("userService1")
    private UserService userService1;

    @Autowired
    @Qualifier("userService2")
    private UserService userService2;


    @Test
    public void testDynamicDataSource() {
        log.info("user1: {}", userService1.findOne("1"));
        log.info("user2: {}", userService2.findOne("2"));
    }

    @Test
    public void testDynamicDataSource1(){
        log.info("user2: {}", userService2.findOne("2"));
    }
}
