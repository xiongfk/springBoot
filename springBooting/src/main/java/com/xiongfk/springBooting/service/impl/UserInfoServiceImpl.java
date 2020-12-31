package com.xiongfk.springBooting.service.impl;

import com.xiongfk.springBooting.mapper.master.LoginRegisterUserMapper;
import com.xiongfk.springBooting.model.LoginRegisterUser;
import com.xiongfk.springBooting.model.LoginRegisterUserExample;
import com.xiongfk.springBooting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/4
 * @Version 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private LoginRegisterUserMapper userMapper;

    @Override
    public int registerUserInfo(LoginRegisterUser loginRegisterUser) {
        LoginRegisterUserExample example = new LoginRegisterUserExample();
        example.createCriteria().andUserNameEqualTo(loginRegisterUser.getUserName());
        List<LoginRegisterUser> userInfoList = userMapper.selectByExample(example);
        Integer register = 0;
        if (null != userInfoList && !userInfoList.isEmpty()) {
            System.out.println("该用户已存在!");
        } else {
            register = userMapper.insert(loginRegisterUser);
        }
        return register;
    }

    @Override
    public LoginRegisterUser loginUserInfo(String userName) {
        LoginRegisterUserExample example = new LoginRegisterUserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<LoginRegisterUser> userInfoList = userMapper.selectByExample(example);
        if (null != userInfoList && !userInfoList.isEmpty()) {
            return userInfoList.get(0);
        }else{
            return null;
        }
    }
}
