package com.xiongfk.springBooting.mq.activeMQ.product.service;

import java.util.Map;

/**
 * ProducerService
 * 接口描述 TODO 定义消息发送接口
 *
 * @Author xiongfk
 * @Date 2019/7/21
 * @Version 1.0
 **/
public interface ProducerService {
    /**
     * 方法描述 TODO 发送点对点队列消息
     *
     * @param
     * @return void
     * @author xiongfk
     * @date 2019/7/21
     */
    void sendMsgQueue();

    /**
     * 方法描述 TODO 发送发布/订阅消息
     *
     * @param
     * @return void
     * @author xiongfk
     * @date 2019/7/21
     */
    void sendMsgTopic();
}
