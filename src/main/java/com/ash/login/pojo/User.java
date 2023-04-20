package com.ash.login.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息
 * @author Wu dz
 * @date 2023/4/19
 */
@Getter
@Setter
public class User {
    private String id;

    private String userCode;

    private String userName;

    private Integer status;
}
