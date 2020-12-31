package com.xiongfk.springBooting.mq.ribaitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能描述 TODO
 * rabbitmq消费者 @RabbitListener(queues = "simpleMsg") 监听名simpleMsg的队列
 * @Author xiongfk
 * @Date 2020/4/8
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "testQueue")
public class RabbitConsumer {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg) {
        System.out.println("[string] recieved message:" + msg);
    }
}
