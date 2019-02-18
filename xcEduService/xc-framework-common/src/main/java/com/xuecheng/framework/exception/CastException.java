package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Auther: wangc
 * @Date: 2019/1/12 23:40
 * @Description: 异常抛出类
 */
public class CastException {

  //使用此静态方法抛出自定义异常
  public static void cast(ResultCode resultCode) {
    throw new CustomException(resultCode);
  }
}
