package com.xiongfk.springBooting.service.impl;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.service.AsyncTestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.Socket;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/10/8
 * @Version 1.0
 **/
@Service
public class AsyncTestServiceImpl extends BaseCommonLog implements AsyncTestService{

    @Override
    //通过@Async注解表明该方法是异步方法，如果注解在类上，那表明这个类里面的所有方法都是异步的。
    @Async
    public void test(int i) {
        Socket socket = new Socket();
        logger.info("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }
}
