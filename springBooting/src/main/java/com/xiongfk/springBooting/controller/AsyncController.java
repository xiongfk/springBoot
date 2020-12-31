package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/9
 * @Version 1.0
 **/
@RestController
public class AsyncController extends BaseCommonLog {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/")
    public String submit(){
        logger.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();

        logger.info("end submit");

        return "success";
    }
}
