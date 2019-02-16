package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: wangc
 * @Date: 2019/1/12 23:42
 * @Description: 异常的捕获类
 */
@ControllerAdvice
public class CatchException {

	private static final Logger LOG = LoggerFactory.getLogger(CatchException.class);
	//ImmutableMap需要使用buidler来构建
	protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();
	//在ImmutableMap中存放异常类型和错误代码的映射, ImmutableMap一旦创建不可更改, 并且线程安全
	private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;



	//捕获自定义异常
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public ResponseResult customException(CustomException e) {
		LOG.error("catch Exception: {}\r\nexception: ", e.getMessage(), e);
		ResultCode resultCode = e.getResultCode();
		ResponseResult responseResult = new ResponseResult(resultCode);
		return responseResult;
	}

	//捕获自定义异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult exception(Exception e) {
		LOG.error("catch Exception: {}\r\nexception: ", e.getMessage(), e);
		if (EXCEPTIONS == null) {
			//builder构建成功
			EXCEPTIONS = builder.build();
		}
		final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
		final ResponseResult responseResult;
		if (resultCode != null) {
			responseResult = new ResponseResult(resultCode);
		} else {
			//返回99999异常
			responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
		}
		return responseResult;
	}

	static {
		//在这里加入一些基础的异常类型判断
		builder.put(HttpMessageNotReadableException.class, CommonCode.INVALIDPARAM);
	}
}
