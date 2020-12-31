package com.xiongfk.springBooting.service.webService.impl;

import com.xiongfk.springBooting.service.webService.UserService;

import javax.jws.WebService;
import java.util.Date;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/12
 * @Version 1.0
 **/
@WebService(name="UserService",////对外发布的服务名
        targetNamespace="http://webService.xiongfk.example.com",
        //指定你想要的名称空间，通常使用使用包名反转
        endpointInterface= "com.xiongfk.springBooting.service.webService.UserService")
        //服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
public class UserServiceImpl implements UserService {

    @Override
    public String getUser(String name) {
        String result = "";
        try {
            result = "UserName:" + name + " - Date:" + new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String listUser() {
        String result = "";
        try {
            result = "Date:" + new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String deleteUser(String id) {
        String result = "";
        try {
            result = "UserId:" + id + " - Date:" + new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String updateUser(String id) {
        String result = "";
        try {
            result = "UserId:" + id + " - Date:" + new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

