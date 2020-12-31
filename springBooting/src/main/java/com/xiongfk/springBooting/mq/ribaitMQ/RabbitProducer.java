package com.xiongfk.springBooting.mq.ribaitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/8
 * @Version 1.0
 **/
@Component
public class RabbitProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void stringSend() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[string] send msg:" + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend("testQueue", dateString);
    }
}
