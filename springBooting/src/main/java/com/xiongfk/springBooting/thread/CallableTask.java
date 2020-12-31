package com.xiongfk.springBooting.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/14
 * @Version 1.0
 **/
@Service
public class CallableTask implements Callable {

    @Override
    public Object call() throws Exception {
        return "hello world";
    }
}
