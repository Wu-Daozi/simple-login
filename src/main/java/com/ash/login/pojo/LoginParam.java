package com.ash.login.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录入参
 * @author Wu dz
 * @date 2023/4/19
 */
@Data
public class LoginParam {
    @NotBlank(message = "登录账号不能为空")
    private String userName;

    @NotBlank(message = "登录密码不能为空")
    private String password;
}
