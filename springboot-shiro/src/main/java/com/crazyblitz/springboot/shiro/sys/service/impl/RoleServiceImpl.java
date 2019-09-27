package com.crazyblitz.springboot.shiro.sys.service.impl;

import com.crazyblitz.springboot.shiro.sys.entity.Role;
import com.crazyblitz.springboot.shiro.sys.mapper.RoleMapper;
import com.crazyblitz.springboot.shiro.sys.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
