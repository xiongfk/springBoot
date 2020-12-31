package com.xiongfk.springBooting.mq.activeMQ.product.service.impl;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.mq.activeMQ.product.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/21
 * @Version 1.0
 **/
@Service
public class ProductServiceImpl extends BaseCommonLog implements ProducerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    @Override
    public void sendMsgQueue() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiongfk");
        map.put("sex", "boy");
        map.put("age", "24");
        //发送队列消息
        jmsMessagingTemplate.convertAndSend(queue, map);
        logger.info("---------生产者发送消息start---------");
    }

    @Override
    public void sendMsgTopic() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiongfk");
        map.put("sex", "boy");
        map.put("age", "24");
        //发送订阅消息
        jmsMessagingTemplate.convertAndSend(topic, map);
        logger.info("---------生产者发送topic消息start---------");
    }
}
