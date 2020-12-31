package com.xiongfk.springBooting.controller;

import org.apache.commons.collections4.Get;
import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 功能描述 TODO
 * 登录注册页面
 * @Author xiongfk
 * @Date 2019/9/5
 * @Version 1.0
 **/
@Controller
public class RegisterLoginController {

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String index() {
        return "register";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
