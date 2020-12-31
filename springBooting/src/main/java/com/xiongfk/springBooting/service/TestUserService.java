package com.xiongfk.springBooting.service;

import com.xiongfk.springBooting.model.User;
import org.springframework.stereotype.Service;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/24
 * @Version 1.0
 **/
@Service
public class TestUserService {

    public String addUser(User user) {
        // 直接编写业务逻辑
        return "success";
    }
}
