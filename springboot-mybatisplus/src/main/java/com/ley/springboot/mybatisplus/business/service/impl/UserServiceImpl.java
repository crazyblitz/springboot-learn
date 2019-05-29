package com.ley.springboot.mybatisplus.business.service.impl;

import com.ley.springboot.mybatisplus.business.entity.User;
import com.ley.springboot.mybatisplus.business.mapper.UserMapper;
import com.ley.springboot.mybatisplus.business.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuenyuan
 * @since 2019-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
