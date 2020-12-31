package com.xiongfk.springBooting.service;

import com.xiongfk.springBooting.model.UserInfo;

/**
 * RedisCommonSevice
 * 接口描述 TODO REDIS查询操作在SERVICE层，MySQL查询仍在DATA层
 *
 * @Author xiongfk
 * @Date 2019/7/17
 * @Version 1.0
 **/
public interface RedisCommonSevice {
    /**
     * 方法描述 TODO 通过用户名查询用户信息
     *
     * @param userName
     * @return com.example.xiongfk.model.UserInfo
     * @author xiongfk
     * @date 2019/7/17
     */
    UserInfo queryByName(Integer sid, String userName);
}
