package com.crazyblitz.springboot.shiro.sys.service;

import com.crazyblitz.springboot.shiro.sys.entity.Permission;
import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuenyuan
 * @since 2019-09-27
 */
public interface UserService extends IService<User> {

    List<Role> getRoles(String userId);

    List<Permission> getPermissions(String roleId);
}
