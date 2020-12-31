package com.xiongfk.springBooting.service.impl;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/9
 * @Version 1.0
 **/
@Service
public class AsyncServiceImpl extends BaseCommonLog implements AsyncService {
    @Override
    @Async(value = "asyncServiceExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
