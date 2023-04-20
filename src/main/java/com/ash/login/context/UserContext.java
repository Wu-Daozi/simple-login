package com.ash.login.context;

import com.ash.login.constant.ReturnCode;
import com.ash.login.exception.BizException;
import com.ash.login.pojo.User;

/**
 * 用户信息上下文
 * 保存当前线程登录的用户信息
 * @author Wu dz
 * @date 2023/4/19
 */
public class UserContext {

    private static final ThreadLocal<User> USER = new ThreadLocal<>();

    private UserContext() {}

    /**
     * 获取用户id
     * @return 用户id
     */
    public static String getUserId() {
        User baseUser = getUser();
        return baseUser.getId();
    }

    /**
     * 获取用户账号
     * @return 用户账号，例如：admin
     */
    public static String getUserCode() {
        User baseUser = getUser();
        return baseUser.getUserCode();
    }

    /**
     * 获取用户名称
     * @return 用户名称，例如：管理员
     */
    public static String getUserName() {
        User baseUser = getUser();
        return baseUser.getUserName();
    }

    /**
     * 获取用户信息
     * @return 用户信息对象
     */
    public static User getUser() {
        User baseUser = UserContext.USER.get();
        if (null == baseUser) {
            throw new BizException("账号已登出", ReturnCode.LOGOUT);
        }
        return baseUser;
    }

    /**
     * 设置用户信息
     * @param baseUser 用户信息对象
     */
    public static void setUser(User baseUser) {
        USER.set(baseUser);
    }

    /**
     * 移除用户信息
     */
    public static void remove(){
        USER.remove();
    }
}
