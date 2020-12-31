package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.utils.LockUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/15
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/lock/")
public class RedissonLockController {
    static final String KEY = "LOCK_KEY";

    @RequestMapping(value = "addLock")
    public Object addLock(){
        //加锁
        LockUtils.lock(KEY);
        try {
            //TODO 处理业务
            System.out.println(" 处理业务。。。");
        } catch (Exception e) {
            //异常处理
        }finally{
            //释放锁
//            LockUtils.unlock(KEY);
        }
        return "SUCCESS";
    }
}
