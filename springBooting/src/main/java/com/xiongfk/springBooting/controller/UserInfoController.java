package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.model.LoginRegisterUser;
import com.xiongfk.springBooting.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述 TODO
 * 用户登录注册信息
 *
 * @Author xiongfk
 * @Date 2019/9/4
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/user/")
public class UserInfoController extends BaseCommonLog {
    @Autowired
    private UserInfoService userInfoService;

    /**
    * 方法描述 TODO 用户注册
    * @author xiongfk
    * @date 2019/9/9
    * @param user, mv]
    * @return org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "register")
    public ModelAndView userRegister(LoginRegisterUser user, ModelAndView mv) {
        Integer result = 0;
        if (null != user && StringUtils.isNotEmpty(user.getUserName()) && StringUtils.isNotEmpty(user.getPassword())) {
            result = userInfoService.registerUserInfo(user);
            if (result > 0) {
                logger.error("用户信息注册成功!");
                mv.setViewName("login");
            } else {
                logger.error("注册失败,请重试!");
                mv.setViewName("register");
            }
        } else {
            logger.error("用户名或密码输入为空,请检查!");
            mv.setViewName("register");
        }
        return mv;
    }

    /**
    * 方法描述 TODO 用户登录
    * @author xiongfk
    * @date 2019/9/9
    * @param user, mv]
    * @return org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "login")
    public ModelAndView login(LoginRegisterUser user, ModelAndView mv) {
        if(null!=user && StringUtils.isNotEmpty(user.getUserName())){
            LoginRegisterUser userInfo= userInfoService.loginUserInfo(user.getUserName());
            if(null==userInfo){
                logger.error("未查询到该用户信息,请重新输入!");
                mv.setViewName("login");
            }else{
                if(userInfo.getPassword().equals(user.getPassword()) && userInfo.getUserName().equals(user.getUserName())){
                    logger.error("用户登录成功!");
                    mv.addObject("userName",user.getUserName());
                    mv.setViewName("success");
                }else{
                    logger.error("用户名或密码不匹配,请重新输入!");
                    mv.setViewName("login");
                }
            }
        }else{
            logger.error("用户名或密码输入为空或,请重新输入!");
            mv.setViewName("login");
        }
        return mv;
    }
}
