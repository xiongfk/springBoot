package com.xiongfk.springBooting.service;

import com.xiongfk.springBooting.exception.BizException;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenService
 * 接口描述 TODO
 * token服务接口
 * @Author xiongfk
 * @Date 2020/5/19
 * @Version 1.0
 **/
public interface TokenService {
    /**
    * 方法描述 TODO 创建token
    * @author xiongfk
    * @date 2020/5/19
    * @return java.lang.String
    */
    String createToken();

    /**
    * 方法描述 TODO 校验token
    * @author xiongfk
    * @date 2020/5/19
    * @param request
    * @return boolean
    */
    boolean checkToken(HttpServletRequest request) throws BizException;
}
