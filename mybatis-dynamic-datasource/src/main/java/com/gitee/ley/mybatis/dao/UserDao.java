package com.gitee.ley.mybatis.dao;

import com.gitee.ley.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from tb_user where user_id = #{param1}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName")
    })
    User findOne(String userId);
}
