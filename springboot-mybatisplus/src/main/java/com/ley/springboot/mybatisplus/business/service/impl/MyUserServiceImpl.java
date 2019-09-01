package com.ley.springboot.mybatisplus.business.service.impl;

import com.ley.springboot.mybatisplus.business.entity.User;
import com.ley.springboot.mybatisplus.business.mapper.UserMapper;
import com.ley.springboot.mybatisplus.business.service.MyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;

@Service
@Slf4j
public class MyUserServiceImpl implements MyUserService {

    private static MyUserService instance;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApplicationContext applicationContext;

    public static MyUserService getInstance() {
        return instance;
    }

    @PostConstruct
    protected void setUp() {
        instance = applicationContext.getBean(MyUserService.class);
        if (AopUtils.isAopProxy(instance)) {
            log.info("instance: {}", "是代理对象");
        }
        if (AopUtils.isAopProxy(instance)) {
            log.info("instance target: {}", AopProxyUtils.getSingletonTarget(instance));
        }
        if (AopUtils.isAopProxy(instance)) {
            log.info("target class: {}", AopUtils.getTargetClass(instance));
        }
        if (AopUtils.isAopProxy(instance)) {
            log.info("proxy interface: {}", Arrays.asList(AopProxyUtils.proxiedUserInterfaces(instance)));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(User user) {
        userMapper.insert(user);
        throw new IllegalArgumentException("exception");
    }
}
