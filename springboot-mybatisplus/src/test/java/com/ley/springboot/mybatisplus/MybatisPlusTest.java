package com.ley.springboot.mybatisplus;

import com.ley.springboot.mybatisplus.business.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisplusApplication.class})
public class MybatisPlusTest {


    @Autowired
    private UserMapper userMapper;


    /**
     *
     * **/
    @Test
    public void testQueryWrapper() {
        System.out.println(userMapper.selectById(1));
    }
}
