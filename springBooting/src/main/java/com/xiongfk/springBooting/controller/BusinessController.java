package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.customAnnotation.AutoIdempotent;
import com.xiongfk.springBooting.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/5/19
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/business/")
public class BusinessController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "getToken",method = RequestMethod.POST)
    public String  getToken(){
        String token = tokenService.createToken();
        return token;
    }

    @AutoIdempotent
    @RequestMapping(value = "Idempotence",method = RequestMethod.POST)
    public String testIdempotence(HttpServletRequest request) {
        return "hello world";
    }
}
