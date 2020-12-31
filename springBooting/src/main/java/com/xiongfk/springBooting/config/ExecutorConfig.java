package com.xiongfk.springBooting.config;

import com.xiongfk.springBooting.base.BaseCommonLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
* 方法描述 TODO 线程池配置类
* @author xiongfk
* @date 2019/11/22
* @param
* @return
*/
@Configuration
@EnableAsync
public class ExecutorConfig extends BaseCommonLog {
    @Value(value = "${myThread.corePoolSize}")
    private Integer corePoolSize;
    @Value(value = "${myThread.maxPoolSize}")
    private Integer maxPoolSize;
    @Value(value = "${myThread.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    //配置队列大小
    @Value(value = "${myThread.queueCapacity}")
    private Integer queueCapacity;
    //配置线程池中的线程的名称前缀
    @Value(value = "${myThread.threadNamePrefix}")
    private String threadNamePrefix;

    @Bean
    public Executor asyncServiceExecutor () {
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
