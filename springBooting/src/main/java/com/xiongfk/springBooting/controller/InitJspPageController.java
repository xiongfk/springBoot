package com.xiongfk.springBooting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能描述 TODO
 * 初始化jsp页面跳转
 * @Author xiongfk
 * @Date 2019/9/12
 * @Version 1.0
 **/
@Controller
public class InitJspPageController {

    @RequestMapping(value = "/uploadFile",method = RequestMethod.GET)
    public String upLoadFile(){
        return "uploadFile";
    }
}
