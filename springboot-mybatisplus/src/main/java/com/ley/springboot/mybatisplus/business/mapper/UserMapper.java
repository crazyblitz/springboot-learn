package com.ley.springboot.mybatisplus.business.mapper;

import com.ley.springboot.mybatisplus.business.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuenyuan
 * @since 2019-04-23
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * get user by id
     *
     * @param userId
     * @return
     **/
    User getUserById(@Param("userId") String userId);
}
