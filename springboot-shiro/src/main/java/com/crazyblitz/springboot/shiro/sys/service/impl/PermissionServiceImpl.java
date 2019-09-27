package com.crazyblitz.springboot.shiro.sys.service.impl;

import com.crazyblitz.springboot.shiro.sys.entity.Permission;
import com.crazyblitz.springboot.shiro.sys.mapper.PermissionMapper;
import com.crazyblitz.springboot.shiro.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuenyuan
 * @since 2019-09-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
