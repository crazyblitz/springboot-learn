package com.ley.springboot.mybatisplus;

import com.ley.springboot.mybatisplus.business.entity.User;
import com.ley.springboot.mybatisplus.business.mapper.UserMapper;
import com.ley.springboot.mybatisplus.business.service.MyUserService;
import com.ley.springboot.mybatisplus.business.service.UserService;
import com.ley.springboot.mybatisplus.business.service.impl.MyUserServiceImpl;
import com.ley.springboot.mybatisplus.utils.SqlSessionFactoryUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisplusApplication.class})
public class MybatisPlusTest {


    @Resource
    private UserMapper userMapper;

    @Resource
    private SqlSessionFactoryUtils sqlSessionFactoryUtils;

    @Resource
    private UserService userService;

    @Resource
    private MyUserService myUserService;

    @Test
    public void testMyUserService(){
        User user=new User();
        user.setUserName("刘恩源");
        System.out.println(Arrays.asList(AopProxyUtils.proxiedUserInterfaces(myUserService)));
        MyUserServiceImpl.getInstance().insertSelective(user);
    }

    /**
     *
     * **/
    @Test
    public void testQueryWrapper() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void testBatchInsert1() {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        User user = new User();
        List<User> userList=new ArrayList<>(1000000);
        int size = 1000000;
        for (int i = 0; i < size; i++) {
            user.setUserName("刘恩源"+i);
            user.setUserId(i);
            userList.add(user);
        }
        userService.saveBatch(userList,5000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testBatchInsert2() {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        User user = new User();
        int size = 500000;
        for (int i = 0; i < size; i++) {
            user.setUserName("刘恩源"+i);
            user.setUserId(i);
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testBatchInsert3(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        User user = new User();
        int size = 10000;
        List<User> userList=new ArrayList<>(10000);
        for (int i = 0; i < size; i++) {
            user.setUserName("刘恩源"+i);
            user.setUserId(i);
            userList.add(user);
        }
        sqlSessionFactoryUtils.insertBatch(userList,"com.ley.springboot.mybatisplus.business.mapper.UserMapper.insert",
                1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
