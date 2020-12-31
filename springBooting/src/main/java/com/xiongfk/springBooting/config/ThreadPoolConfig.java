package com.xiongfk.springBooting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 功能描述 TODO
 * 线程池配置类
 * @Author xiongfk
 * @Date 2020/4/10
 * @Version 1.0
 **/
@Component
public class ThreadPoolConfig {
    @Value(value = "${threadPool.corePoolSize}")
    private Integer corePoolSize;
    @Value(value = "${threadPool.maxPoolSize}")
    private Integer maxPoolSize;
    @Value(value = "${threadPool.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    @Value(value = "${threadPool.capaticy}")
    private Integer capaticy;

    public ExecutorService treadPoolExecutor(){
        ExecutorService executorService =
                new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveSeconds,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(capaticy),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        return executorService;
    }
}
