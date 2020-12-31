package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.config.ThreadPoolConfig;
import com.xiongfk.springBooting.thread.CallableTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/14
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/callableTask/")
public class CallableTaskController {

    @Autowired
    private CallableTask callableTask;
    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @RequestMapping(value = "sendMsg")
    public String sendMsg(){
        try {
            return callableTask.call().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "threadPool")
    public void threadPool(){
        ExecutorService executorService = threadPoolConfig.treadPoolExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("==================");
            }
        });
    }
}
