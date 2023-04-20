package com.ash.login.controller.base;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应信息
 * @author Wu dz
 * @date 2023/4/19
 */
@Getter
@Setter
public class BaseRsp<T> {
	/**
	 * 响应代码
	 */
	private Integer code;
	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 响应数据
	 */
	private T data;
	
	public BaseRsp() {}
	
	public BaseRsp(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public BaseRsp(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
