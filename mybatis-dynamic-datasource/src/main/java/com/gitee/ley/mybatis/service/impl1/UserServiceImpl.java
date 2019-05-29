package com.gitee.ley.mybatis.service.impl1;

import com.gitee.ley.mybatis.dao.UserDao;
import com.gitee.ley.mybatis.entity.User;
import com.gitee.ley.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService1")
@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public User findOne(String userId) {
        return userDao.findOne(userId);
    }
}
