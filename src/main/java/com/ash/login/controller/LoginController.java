package com.ash.login.controller;

import com.ash.login.controller.base.BaseController;
import com.ash.login.controller.base.BaseRsp;
import com.ash.login.entity.TUser;
import com.ash.login.enums.DeleteFlagEnum;
import com.ash.login.exception.BizException;
import com.ash.login.mapper.TUserMapper;
import com.ash.login.pojo.LoginParam;
import com.ash.login.pojo.User;
import com.ash.login.util.TimeCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录controller
 * @author Wu dz
 * @date 2023/4/19
 */
@RestController
public class LoginController extends BaseController {

    /**
     * token前缀
     */
    private static final String TOKEN_PREFIX = "ASH_TOKEN";

    @Resource
    private TUserMapper userMapper;

    @PostMapping(value = "/login")
    public BaseRsp<String> admLogin(@Valid @RequestBody LoginParam param) {
        // 用户名和密码，这里可以配合前端做加解密
        String userName = param.getUserName();
        String password = param.getPassword();

        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code", userName).eq("delete_flag", DeleteFlagEnum.NO.getValue());
        TUser user = userMapper.selectOne(queryWrapper);
        if (null == user) {
            throw new BizException("账号或密码错误");
        }

        // 校验密码
        if (!user.getPassword().equals(StringUtils.upperCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8))))) {
            throw new BizException("账号或密码错误");
        }
        User baseUser = new User();
        BeanUtils.copyProperties(user, baseUser);
        // 生成token并存入缓存，10分钟过期
        String uuid = generateId();
        String token = DigestUtils.md5DigestAsHex(StringUtils.join(TOKEN_PREFIX, uuid).getBytes(StandardCharsets.UTF_8));
        TimeCache.put(token, baseUser, 10, TimeUnit.MINUTES);
        return this.success(token);
    }

    /**
     * 生成uuid
     * @return uuid
     */
    private String generateId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
