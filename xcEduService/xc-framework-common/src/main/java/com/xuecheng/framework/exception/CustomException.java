package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Auther: wangc
 * @Date: 2019/1/12 22:55
 * @Description: 自定义异常类, 继承运行时异常
 */
public class CustomException extends RuntimeException {

	private ResultCode resultCode;

	//自定义异常的构造方法
	public CustomException(ResultCode resultCode) {
		super("错误代码: " + resultCode.code() + "错误信息" + resultCode.message());
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return this.resultCode;
	}
}
