package com.ash.login.controller.base;

import com.ash.login.constant.ReturnCode;

/**
 * 基础controller
 * @author Wu dz
 * @date 2023/4/19
 */
public class BaseController {

  protected <T> BaseRsp<T> success(T data) {
    BaseRsp<T> vo = new BaseRsp<>();
    vo.setCode(ReturnCode.SUCCESS);
    vo.setData(data);
    return vo;
  }

  protected <T> BaseRsp<T> error(String message) {
    BaseRsp<T> vo = new BaseRsp<>();
    vo.setCode(ReturnCode.FAILURE);
    vo.setMsg(message);
    return vo;
  }
}
