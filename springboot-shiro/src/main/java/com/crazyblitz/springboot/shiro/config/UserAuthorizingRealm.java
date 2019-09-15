package com.crazyblitz.springboot.shiro.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.entity.User;
import com.crazyblitz.springboot.shiro.sys.service.UserService;
import com.crazyblitz.springboot.shiro.utils.security.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * @author liuenyuan
 */
@Slf4j
public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * shiro的权限配置方法
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if (log.isInfoEnabled()) {
            log.info("权限配置-->doGetAuthorizationInfo");
        }


        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        if (log.isInfoEnabled()) {
            log.info("----------------------------->" + principals.getPrimaryPrincipal());
        }

        User user = (User) principals.getPrimaryPrincipal();

        if (log.isInfoEnabled()) {
            log.info("doGetAuthorizationInfo() user: {}", user);
        }
        List<Role> roles = userService.getRoles(user.getUserId());

        for (Role role : roles) {

            authorizationInfo.addRole(role.getRoleName());

            // 如果有权限，应该增加所有角色对应的权限
            // authorizationInfo.addStringPermission()
        }

        if (log.isInfoEnabled()) {
            log.info("用户: {},具有的角色: {}", user.getUserName(), authorizationInfo.getRoles());
            log.info("用户: {},具有的权限: {}", user.getUserName(), authorizationInfo.getStringPermissions());
        }

        return authorizationInfo;
    }

    /**
     * shiro的身份验证方法
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (log.isInfoEnabled()) {
            log.info("正在验证身份...");
        }

        SimpleAuthenticationInfo info;

        //将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //从转换后的token中获取用户名
        String username = upToken.getUsername();
        //查询数据库，得到用户
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserName, username));
        if (user == null) {
            if (log.isInfoEnabled()) {
                log.info("没有用户: {}", username);
            }
            return null;
        }

        //得到加密密码的盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUserSalt());

        //得到盐值加密后的密码: 只用于方便数据库测试,后期不会用到。
        Object md = new SimpleHash(PasswordUtils.ALGORITHM_NAME, upToken.getPassword(), salt, PasswordUtils.HASH_ITERATIONS);

        if (log.isInfoEnabled()) {
            log.info("加密密码的盐: {}", salt);
            log.info("盐值加密后的密码: {}", md);
        }

        //TODO: 用户名;用户密码;加密盐;realm name
        info = new SimpleAuthenticationInfo(user, user.getUserPassword(), salt, getName());
        return info;
    }

}