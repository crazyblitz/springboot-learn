package com.crazyblitz.springboot.shiro.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.entity.User;
import com.crazyblitz.springboot.shiro.sys.entity.UserRole;
import com.crazyblitz.springboot.shiro.sys.mapper.UserMapper;
import com.crazyblitz.springboot.shiro.sys.service.RoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserRoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuenyuan
 * @since 2019-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<Role> getRoles(String userId) {
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId));
        List<Role> roles = new ArrayList<>(userRoles.size());
        for (UserRole userRole : userRoles) {
            roles.add(roleService.getById(userRole.getRoleId()));
        }
        return roles;
    }
}
