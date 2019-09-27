package com.crazyblitz.springboot.shiro.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.crazyblitz.springboot.shiro.sys.entity.*;
import com.crazyblitz.springboot.shiro.sys.mapper.UserMapper;
import com.crazyblitz.springboot.shiro.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuenyuan
 * @since 2019-09-27
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;

    @Override
    public List<Role> getRoles(String userId) {
        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery()
                .eq(UserRole::getUserId, userId));
        List<Role> roles = Collections.emptyList();
        if (!CollectionUtils.isEmpty(userRoles)) {
            List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            roles = roleService.list(Wrappers.<Role>lambdaQuery().in(Role::getId, roleIds));
        }
        if (log.isInfoEnabled()) {
            log.info("userId: {},roles: {}", userId, roles);
        }
        return roles;
    }

    @Override
    public List<Permission> getPermissions(String roleId) {
        List<RolePermission> rolePermissions = rolePermissionService.list(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getRoleId, roleId));
        List<Permission> permissions = Collections.emptyList();
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            List<String> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
            permissions = permissionService.list(Wrappers.<Permission>lambdaQuery().in(Permission::getId, permissionIds));
        }
        if (log.isInfoEnabled()) {
            log.info("roleId: {},permissions: {}", roleId, permissions);
        }
        return permissions;
    }
}
