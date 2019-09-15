package com.crazyblitz.springboot.shiro;


import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.entity.User;
import com.crazyblitz.springboot.shiro.sys.entity.UserRole;
import com.crazyblitz.springboot.shiro.sys.service.RoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserRoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testTx() {
        System.out.println(AopUtils.isAopProxy(userService));
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        userService.save(user);
        throw new IllegalArgumentException("123");
    }


    @Test
    public void testSaveUser() {
        String userId = "7e639fdd-04de-44ff-88ae-ad9d42b3b9ce";
        Role adminRole = new Role();
        String adminRoleId = UUID.randomUUID().toString();
        adminRole.setRoleId(adminRoleId);
        adminRole.setRoleName("admin");
        adminRole.setRoleDesc("管理员");
        roleService.save(adminRole);

        Role roleUser = new Role();
        String roleUserId = UUID.randomUUID().toString();
        roleUser.setRoleName("user");
        roleUser.setRoleId(roleUserId);
        roleUser.setRoleDesc("用户");
        roleService.save(roleUser);

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(adminRoleId);
        userRole.setUserRoleId(UUID.randomUUID().toString());
        userRoleService.save(userRole);

        UserRole userRole1 = new UserRole();
        userRole1.setUserId(userId);
        userRole1.setRoleId(roleUserId);
        userRole1.setUserRoleId(UUID.randomUUID().toString());
        userRoleService.save(userRole1);
    }
}
