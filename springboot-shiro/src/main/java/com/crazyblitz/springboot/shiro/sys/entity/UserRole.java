package com.crazyblitz.springboot.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuenyuan
 * @since 2019-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_role_id")
    private String userRoleId;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;


}
