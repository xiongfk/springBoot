package com.xiongfk.springBooting.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class ThreadTask implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    //测试可重入锁加锁
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int t = 0;t<100;t++){
            lock.lock();
            Integer addLockTimes = lock.getHoldCount();
            System.out.println("加锁次数:" + addLockTimes +"  ----  " + Thread.currentThread().getName());
        }
    }
}
