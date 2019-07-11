package com.ley.springboot.mybatis.tk.dao;

import com.ley.springboot.mybatis.tk.bean.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends MySqlMapper<User>, Mapper<User> {

}
