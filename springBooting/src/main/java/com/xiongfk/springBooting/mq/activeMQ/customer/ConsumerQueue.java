package com.xiongfk.springBooting.mq.activeMQ.customer;

import com.xiongfk.springBooting.base.BaseCommonLog;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述 TODO queue消息消费者实现
 *
 * @Author xiongfk
 * @Date 2019/7/20
 * @Version 1.0
 **/
@Component
public class ConsumerQueue extends BaseCommonLog {

    @JmsListener(destination = "activeMq.queue")
    public void receiveQueue(Map<String, String> map) {
        logger.info("-----------queue队列消费者消费消息start-----------");
        if (null != map && !map.isEmpty()) {
            logger.info("");
            String name = map.get("name");
            String age = map.get("age");
            String sex = map.get("sex");
            logger.info("\n 姓名:" + name + "\n 性别:" + sex + "\n 年龄:" + age);
        }
        logger.info("-----------queue-队列消费者消费消息end-----------");
    }
}
