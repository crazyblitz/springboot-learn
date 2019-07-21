package com.ley.crazyblitz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ley.crazyblitz.business.entity.User;
import com.ley.crazyblitz.business.service.UserService;
import com.ley.crazyblitz.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda().eq(User::getUserName, userName);
        User user = userService.getOne(wrapper);
        if (user != null) {
            request.setAttribute("userInfo", user);
            return JwtUserFactory.create(user);
        } else {
            return null;
        }
    }
}
