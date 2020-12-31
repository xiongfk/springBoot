package com.xiongfk.springBooting.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.config.RabbitConfig;
import com.xiongfk.springBooting.model.Account;
import com.xiongfk.springBooting.service.AccountService;
import com.xiongfk.springBooting.utils.RedisUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AccountController
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/account/")
@ConfigurationProperties(prefix = "define")
public class AccountController extends BaseCommonLog {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisUtils redisUtils;
    private String keys;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ReentrantLock lock = new ReentrantLock();

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @RequestMapping(value = "updateByName")
    @Transactional
    public Integer updateByName() throws Exception {
        String accountName = "aaa";
        Integer result = accountService.updateByName(accountName);
        int i = 1 / 0;
        return result;
    }

    @RequestMapping(value = "keys")
    public Set<String> gets() {
        Set<String> set = redisUtils.keys("userId_");
        for (String s : set) {
            String str = s.substring(s.indexOf("u"), s.length());
            String json = redisUtils.get(str);
            System.out.println(json);
            if (StringUtils.isNotEmpty(json)) {
                redisUtils.delete(str);
            }
        }
        System.out.println(set.size());
        return set;
    }

    @RequestMapping(value = "addStr")
    public void addStr() {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", "15300781963");
        map.put("name", "xiongfk1");
        map.put("card_code", "1009876514");
        map.put("arrive_time", "2020-02-20");
        map.put("arrive_text", "2张20元首旅如家优惠券");
        map.put("source", "Phone");
        map.put("token", "1qawedsqwedjo1");
        String json = JSONObject.toJSONString(map);
        redisUtils.set("userId_" + 1009876514, json);
        System.out.println("===============");
    }

    @RequestMapping(value = "getKey", method = RequestMethod.GET)
    public String getKey(String key) {
        String json = redisUtils.get(key);
        ConvertDTO convertDTO = JSONObject.parseObject(json, ConvertDTO.class);
        boolean b = redisUtils.lock(key, json);
        if (!b) {
            System.out.println(111111111);
        } else {
            System.out.println(222222222);
        }
        System.out.println(convertDTO);
        return json;
    }

    @RequestMapping(value = "removeKey", method = RequestMethod.GET)
    public void remomveKey(String key) {
        redisUtils.delete(key);
    }

    @RequestMapping(value = "addHash")
    public void setHash() {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", "15300781962");
        map.put("name", "xiongfk");
        map.put("card_code", "1009876515");
        map.put("arrive_time", "2020-02-20");
        map.put("arrive_text", "2张20元首旅如家优惠券");
        map.put("source", "Phone");
        map.put("token", "1qawedsqwedjo2");
        String json = JSONObject.toJSONString(map);
        redisUtils.setHash("userId_", "userId_1009876515", json);
    }

    @RequestMapping(value = "increment")
    public Object incrCounter() {
        Object obj = redisUtils.counter("userId", "userIds");
        if (obj.toString().contains("userId")) {
            String newKey = obj.toString();
            keys = newKey;
            return keys;
        } else {
            Long incr = Long.valueOf(obj.toString());
            System.out.println(keys);
            long t = Integer.valueOf(incr.toString()) / 10;
            System.out.println(t);
            return incr;
        }
    }

    @RequestMapping(value = "reduceCounter", method = RequestMethod.GET)
    public void reduceCounter(String key, Integer redTimes) {
        if (null == redTimes || 0 == redTimes) {
            redTimes = 1;
        }
        for (int i = 0; i < redTimes; i++) {
            redisUtils.counter(key);
        }
    }

    @RequestMapping(value = "getHash")
    public String getHash(String key) {
        Map<String, Object> map = redisUtils.getHash(key);
        if (null != map && !map.isEmpty()) {
            return map.toString();
        }
        String s = redisUtils.getHash("userId_", key);
        return s;
    }

    @RequestMapping(value = "delHash")
    public void delHash() {
        redisUtils.delHash("userId_", "userId_1009876514");
    }

    @RequestMapping(value = "delAllHash", method = RequestMethod.GET)
    public void delAllHash(String key) {
        redisUtils.delHash(key, "");
    }

    @RequestMapping(value = "addLock", method = RequestMethod.GET)
    public String addLock(String key) {
        String value = String.valueOf(System.currentTimeMillis() + 10000);
        logger.info("value" + value);

        String values = redisUtils.get(key);
        if (StringUtils.isNotEmpty(values)) {
            //ReentrantLock可重入锁
            lock.lock();
            boolean checkLock = lock.isHeldByCurrentThread();
            if (checkLock) {
                logger.info("已加锁:" + checkLock);
                redisUtils.delete(key);
                redisUtils.set(key, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                lock.unlock();
                checkLock = lock.isHeldByCurrentThread();
                logger.info("已加锁:" + checkLock);
            } else {
                redisUtils.set(key, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                logger.info("未加锁:" + checkLock);
                logger.info(System.currentTimeMillis() + "==============22");
            }
        }

        //redis 分布式锁
//        boolean b = redisUtils.lock(key , value);
//        String isLock = b == true ? "已加锁":"未加锁";
//        logger.info("是否加锁:" + b + isLock);
//        if(!b){
//            logger.info(System.currentTimeMillis() + "==============11" + isLock);
//        }else{
//            logger.info(System.currentTimeMillis() + "==============22" + isLock);
//        }
        return "sss";
    }

    @RequestMapping(value = "sendMsg")
    public String sendMsg() {
        rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, "hello world");
        rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, "hello worl11d");
        return "123";
    }

    @RequestMapping(value = "receiveMsg")
    public void receiveMsg() {
        Object obj = rabbitTemplate.convertSendAndReceive(RabbitConfig.DEFAULT_BOOK_QUEUE).toString();
        System.out.println(obj + "=================");
    }

    @RequestMapping(value = "canReentrantLocks")
    public void canReentrantLocks() {
        System.out.println("当前线程:" + Thread.currentThread().getName());
        boolean isLocked = lock.isLocked();//此锁是否由任意线程保持
        if (isLocked) {
            System.out.println("已加锁=========");
            Integer addLockTimes = lock.getHoldCount();
            System.out.println("加锁次数11:" + addLockTimes);
        } else {
            lock.lock();
            Integer addLockTimes = lock.getHoldCount();
            System.out.println("加锁次数22:" + addLockTimes);
        }
    }
    //接收json参数
    @RequestMapping(value = "getJson", method = RequestMethod.POST)
    public String getJson(@RequestBody JSONObject jsonParam) {
        String recive = jsonParam.toJSONString();
        logger.info(jsonParam.toJSONString());
        return recive;
    }
}