package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.mq.activeMQ.product.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 功能描述 TODO 消息发送控制层
 *
 * @Author xiongfk
 * @Date 2019/7/21
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/producer/")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "getSendMsg-queue")
    public String getSendMsgQueue(Map<String, String> map) throws Exception {
        producerService.sendMsgQueue();
        return "queue";
    }

    @RequestMapping(value = "getSendMsg-topic")
    public String getSendMsgTopic(Map<String, String> map) {
        producerService.sendMsgTopic();
        return "topic";
    }
}
