package com.ash.login.controller;

import com.ash.login.controller.base.BaseController;
import com.ash.login.controller.base.BaseRsp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统controller
 * @author Wu dz
 * @date 2023/4/19
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController extends BaseController {

    /**
     * 测试接口
     */
    @GetMapping(value = "/test")
    public BaseRsp<String> test() {
        return this.success("success!");
    }

}
