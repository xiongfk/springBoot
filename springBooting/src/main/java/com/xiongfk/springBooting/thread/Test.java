package com.xiongfk.springBooting.thread;

import java.util.concurrent.*;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/6/28
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0, 2, 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int t = 0; t<10; t++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1111);
                }
            });
        }
//        executorService.shutdown();
//        System.out.println(((ThreadPoolExecutor) executorService).getCompletedTaskCount());
    }
}
