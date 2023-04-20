package com.ash.login.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息
 * @author Wu dz
 * @date 2023/4/19
 */
@Getter
@Setter
@TableName(value = "t_user")
public class TUser {

    @TableId
    private String id;

    @TableField(value = "user_code")
    private String userCode;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "delete_flag")
    private Integer deleteFlag;

    @TableField(value = "create_time")
    private Date createTime;
}
