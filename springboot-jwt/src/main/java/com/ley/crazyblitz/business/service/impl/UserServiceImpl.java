package com.ley.crazyblitz.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ley.crazyblitz.business.entity.User;
import com.ley.crazyblitz.business.mapper.UserMapper;
import com.ley.crazyblitz.business.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhiyuan
 * @since 2019-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
