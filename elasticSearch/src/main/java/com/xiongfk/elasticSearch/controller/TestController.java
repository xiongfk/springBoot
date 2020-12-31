package com.xiongfk.elasticSearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/17
 * @Version 1.0
 **/
@RestController
public class TestController {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello (String name){
        return "hello,I'm " + name;
    }
}
