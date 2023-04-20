package com.ash.login.filter;

import com.ash.login.constant.ReturnCode;
import com.ash.login.context.UserContext;
import com.ash.login.exception.BizException;
import com.ash.login.pojo.User;
import com.ash.login.util.TimeCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 请求拦截器
 * @author Wu dz
 * @date 2023/4/19
 */
public class RequestInterceptor implements HandlerInterceptor {

    /**
     * 预处理拦截器
     * 校验token是否有效
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new BizException("用户已登出", ReturnCode.LOGOUT);
        }
        try {
            User user = (User) TimeCache.get(authorization);
            if (null == user) {
                throw new BizException("用户已登出", ReturnCode.LOGOUT);
            }
            UserContext.setUser(user);
            // token续期 10分钟
            TimeCache.put(authorization, user, 10, TimeUnit.MINUTES);
        } catch (BizException e) {
            throw new BizException("用户已登出", ReturnCode.LOGOUT);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束移除用户上下文信息
        UserContext.remove();
    }
}
