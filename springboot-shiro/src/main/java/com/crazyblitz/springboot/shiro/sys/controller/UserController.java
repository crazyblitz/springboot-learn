package com.crazyblitz.springboot.shiro.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.entity.User;
import com.crazyblitz.springboot.shiro.sys.entity.UserRole;
import com.crazyblitz.springboot.shiro.sys.service.RoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserRoleService;
import com.crazyblitz.springboot.shiro.sys.service.UserService;
import com.crazyblitz.springboot.shiro.utils.constants.CookieConstants;
import com.crazyblitz.springboot.shiro.utils.constants.ProjectConstants;
import com.crazyblitz.springboot.shiro.utils.security.PasswordUtils;
import com.crazyblitz.springboot.shiro.utils.web.result.Result;
import com.crazyblitz.springboot.shiro.utils.web.result.ResultCode;
import com.crazyblitz.springboot.shiro.utils.web.result.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangxu
 * @since 2018-11-12
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
@Api("User Controller")
@RequiresAuthentication
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;


    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Result userLogin(@RequestParam String userName, @RequestParam String plainPassword, HttpServletResponse response) {
        Result result;
        UsernamePasswordToken token = new UsernamePasswordToken(userName, plainPassword);
        //获取当前的登录人
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> userMap = new HashMap<>(2);
        try {
            currentUser.login(token);
            PrincipalCollection principals = currentUser.getPrincipals();
            User realUser = (User) principals.getPrimaryPrincipal();

            Cookie userIdCookie = new Cookie(CookieConstants.USER_COOKIE_ID, realUser.getUserId());
            userIdCookie.setPath("/");
            response.addCookie(userIdCookie);
            log.info("current login user: {}", realUser);


            userMap.put("roles", userService.getRoles(realUser.getUserId()));
            userMap.put("userName", realUser.getUserName());
            result = Result.builder().code(ResultCode.SUCCESS.code()).message("登录成功")
                    .data(userMap).build();
        } catch (UnknownAccountException ua) {
            //TODO: 用户名错误
            result = Result.builder().code(ResultCode.UNAUTHORIZED.code()).message("用户不存在,请先注册")
                    .data(false).build();
        } catch (IncorrectCredentialsException ic) {
            //TODO: 密码错误
            result = Result.builder().code(ResultCode.UNAUTHORIZED.code()).message("登录密码不正确,请重新登录")
                    .data(false).build();
        }
        return result;
    }


    @ApiOperation("/管理员查询用户列表(模糊查询)")
    @GetMapping("/list")
    @RequiresRoles(value = {ProjectConstants.ROLE_ADMIN})
    public Result listUsers(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) String userName) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        if (StringUtils.hasText(userName)) {
            page = userService.page(page, new QueryWrapper<User>().lambda().like(User::getUserName, userName).orderByDesc(User::getCreateTime));
        } else {
            page = userService.page(page, new QueryWrapper<User>().lambda().orderByDesc(User::getCreateTime));

        }
        return ResultEntity.success("查询用户列表成功", page);
    }


    @ApiOperation("修改用户密码")
    @PostMapping("/updateUserPwd")
    @RequiresRoles(value = {ProjectConstants.ROLE_ADMIN, ProjectConstants.ROLE_USER})
    public Result updateUser(@RequestParam String userId, @RequestParam String plainPassword) {
        User user = new User();
        user.setUserId(userId);
        String newSalt = UUID.randomUUID().toString();
        String newPassword = PasswordUtils.renewPassword(plainPassword, newSalt);
        user.setUserPassword(newPassword);
        user.setUserSalt(newSalt);
        return ResultEntity.success("更新成功", userService.updateById(user));
    }


    @ApiOperation("根据用户名查询用户")
    @PostMapping("/{userName}")
    @RequiresRoles(value = {ProjectConstants.ROLE_ADMIN, ProjectConstants.ROLE_USER})
    public Result updateUser(@PathVariable String userName) {
        boolean findResult = false;

        User findUser = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserName, userName));

        findResult = findUser != null;

        if (findResult) {
            return ResultEntity.success("有[" + userName + "]用户");
        } else {
            return ResultEntity.error("没有[" + userName + "]用户");
        }
    }


    @ApiOperation("管理员删除用户")
    @DeleteMapping("/{userId}")
    @RequiresRoles(value = {ProjectConstants.ROLE_ADMIN})
    public Result deleteUser(@PathVariable String userId) {
        boolean result = userService.removeById(userId);
        // 删除关联的角色
        List<UserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
        System.out.println(userRoles.stream().map(UserRole::getUserRoleId).collect(Collectors.toList()));
        userRoleService.removeByIds(userRoles.stream().map(UserRole::getUserRoleId).collect(Collectors.toList()));
        log.info("result: {}", result);
        if (result) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败");
        }
    }

    @ApiOperation("用户注册")
    @GetMapping("/register")
    public Result userRegister(@RequestParam String userName, @RequestParam String plainPassword) {
        String salt = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        User user = User.builder().userId(userId)
                .userName(userName).userSalt(salt).userPassword(PasswordUtils.renewPassword(plainPassword, salt))
                .createTime(new Date(System.currentTimeMillis())).build();
        userService.save(user);

        Role roleUser = roleService.getOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleName, ProjectConstants.ROLE_USER));
        String roleUserId = roleUser.getRoleId();

        UserRole userRole = new UserRole();
        userRole.setRoleId(roleUserId);
        userRole.setUserId(userId);
        userRole.setUserRoleId(UUID.randomUUID().toString());
        userRoleService.save(userRole);

        if (log.isInfoEnabled()) {
            log.info("register a user: {},user role: {}", user, userRole);
        }
        Result result = Result.builder().code(ResultCode.SUCCESS.code()).message("注册成功")
                .data(true).build();
        return result;
    }


    @ApiOperation("用户注销")
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultEntity.success("用户登出成功");
    }

}
