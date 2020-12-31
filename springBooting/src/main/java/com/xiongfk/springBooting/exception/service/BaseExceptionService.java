package com.xiongfk.springBooting.exception.service;

/**
 * 接口描述 TODO 异常处理 基础的接口类，自定义的错误描述枚举类需实现该接口
 * @Author xiongfk
 * @Date 2020/4/30
 * @Version 1.0
 **/
public interface BaseExceptionService {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
