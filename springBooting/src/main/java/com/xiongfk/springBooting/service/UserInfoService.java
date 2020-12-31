package com.xiongfk.springBooting.service;

import com.xiongfk.springBooting.model.LoginRegisterUser;

/**
 * UserInfoService
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/4
 * @Version 1.0
 **/
public interface UserInfoService {
    /**
     * 方法描述 TODO 注册用户信息
     *
     * @param loginRegisterUser
     * @return int
     * @author xiongfk
     * @date 2019/9/4
     */
    int registerUserInfo(LoginRegisterUser loginRegisterUser);

    /**
     * 方法描述 TODO 用户登录
     *
     * @param userName
     * @return com.example.xiongfk.model.LoginRegisterUser
     * @author xiongfk
     * @date 2019/9/4
     */
    LoginRegisterUser loginUserInfo(String userName);
}
