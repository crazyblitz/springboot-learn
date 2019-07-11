package com.ley.springboot.mybatis.tk;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ley.springboot.mybatis.tk.bean.User;
import com.ley.springboot.mybatis.tk.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private Gson gson;

    @Test
    public void testPageHelper() {
        PageHelper.startPage(2, 2);
        List<User> userList = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        log.info("pages: {},pageList: {}", pageInfo.getPages(), gson.toJson(pageInfo.getList()));
    }


    @Test
    public void testTkMapper() {
        log.info("get: {}", userMapper.selectByPrimaryKey(1));
    }
}
