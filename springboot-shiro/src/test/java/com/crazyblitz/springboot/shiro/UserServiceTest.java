package com.crazyblitz.springboot.shiro;


import com.crazyblitz.springboot.shiro.sys.entity.*;
import com.crazyblitz.springboot.shiro.sys.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootShiroApplication.class})
public class UserServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    public void testTx() {
        System.out.println(AopUtils.isAopProxy(userService));
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        userService.save(user);
        throw new IllegalArgumentException("123");
    }

    @Test
    public void testPermission() {
        String adminRoleId = "1999105c-2748-4710-918a-f601547617aa";
        String userRoleId = "5aa291f0-0a66-4a59-ad90-4b28735e091a";

        List<Permission> permissions = new ArrayList<>(4);
        Permission permission = new Permission();
        String permissionId = UUID.randomUUID().toString();
        permission.setId(permissionId);
        permission.setDescription("删除用户");
        permission.setName("删除用户");
        permissions.add(permission);

        Permission permission1 = new Permission();
        String permissionId1 = UUID.randomUUID().toString();
        permission1.setId(permissionId1);
        permission1.setDescription("新增用户");
        permission1.setName("新增用户");
        permissions.add(permission1);

        Permission permission2 = new Permission();
        String permissionId2 = UUID.randomUUID().toString();
        permission2.setId(permissionId2);
        permission2.setDescription("更新用户");
        permission2.setName("更新用户");
        permissions.add(permission2);

        Permission permission3 = new Permission();
        String permissionId3 = UUID.randomUUID().toString();
        permission3.setId(permissionId3);
        permission3.setDescription("查询用户");
        permission3.setName("查询用户");
        permissions.add(permission3);
       // permissionService.saveBatch(permissions);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(UUID.randomUUID().toString());
        rolePermission.setPermissionId("401d4093-67ef-43f3-9147-5f773b1e0efa");
        rolePermission.setRoleId(adminRoleId);

        RolePermission rolePermission1 = new RolePermission();
        rolePermission1.setId(UUID.randomUUID().toString());
        rolePermission1.setPermissionId("5ce597bd-5a72-4536-b34b-3c557a5926da");
        rolePermission1.setRoleId(adminRoleId);

        RolePermission rolePermission2 = new RolePermission();
        rolePermission2.setId(UUID.randomUUID().toString());
        rolePermission2.setPermissionId("7d08e0c4-e4e6-465b-9fac-407b66018da6");
        rolePermission2.setRoleId(adminRoleId);

        RolePermission rolePermission3 = new RolePermission();
        rolePermission3.setId(UUID.randomUUID().toString());
        rolePermission3.setPermissionId("69508e5b-07c9-4bd8-9b18-124f869a8591");
        rolePermission3.setRoleId(adminRoleId);

        List<RolePermission> rolePermissions=new ArrayList<>(4);
        rolePermissions.add(rolePermission);
        rolePermissions.add(rolePermission1);
        rolePermissions.add(rolePermission2);
        rolePermissions.add(rolePermission3);
        System.out.println(rolePermissions);
        rolePermissionService.saveBatch(rolePermissions);
    }


    @Test
    public void testSaveUser() {
        String userId = "7e639fdd-04de-44ff-88ae-ad9d42b3b9ce";
        Role adminRole = new Role();
        String adminRoleId = UUID.randomUUID().toString();
        adminRole.setId(adminRoleId);
        adminRole.setName("admin");
        adminRole.setDescription("管理员");
        roleService.save(adminRole);

        Role roleUser = new Role();
        String roleUserId = UUID.randomUUID().toString();
        roleUser.setId("user");
        roleUser.setName(roleUserId);
        roleUser.setDescription("用户");
        roleService.save(roleUser);

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(adminRoleId);
        userRole.setId(UUID.randomUUID().toString());
        userRoleService.save(userRole);

        UserRole userRole1 = new UserRole();
        userRole1.setUserId(userId);
        userRole1.setRoleId(roleUserId);
        userRole1.setId(UUID.randomUUID().toString());
        userRoleService.save(userRole1);
    }
}
