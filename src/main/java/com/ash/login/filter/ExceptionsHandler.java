package com.ash.login.filter;

import com.ash.login.constant.ReturnCode;
import com.ash.login.controller.base.BaseRsp;
import com.ash.login.exception.BizException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 * @author Wu dz
 * @date 2023/4/19
 */
@ControllerAdvice
public class ExceptionsHandler {

	/**
	 * 参数校验
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public BaseRsp<Object> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
		FieldError fieldError = e.getBindingResult().getFieldError();
		BaseRsp<Object> rsp = new BaseRsp<>();
		rsp.setCode(ReturnCode.FAILURE);
		if (null != fieldError) {
			rsp.setMsg(fieldError.getDefaultMessage());
		}
		return rsp;
	}
	
	/**
	 * 不支持的http请求Method
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public BaseRsp<Object> httpRequestMethodNotSupportedHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
		BaseRsp<Object> rsp = new BaseRsp<>();
		rsp.setCode(ReturnCode.FAILURE);
		rsp.setMsg(e.getMessage());
		return rsp;
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public BaseRsp<Object> bizExceptionHandler(HttpServletRequest request, BizException e) {
		BaseRsp<Object> rsp = new BaseRsp<>();
		rsp.setCode(e.getErrorCode());
		rsp.setMsg(e.getMessage());
		return rsp;
	}


	/**
	 * 其他异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseRsp<Object> defaultExceptionHandler(HttpServletRequest request, Exception e) {
		BaseRsp<Object> rsp = new BaseRsp<>();
		rsp.setCode(ReturnCode.FAILURE);
		rsp.setMsg("服务器异常");
		return rsp;
	}
}
