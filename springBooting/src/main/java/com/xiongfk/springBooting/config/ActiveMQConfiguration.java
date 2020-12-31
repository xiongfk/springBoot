package com.xiongfk.springBooting.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 功能描述 TODO 定义队列||主题
 *
 * @Author xiongfk
 * @Date 2019/7/20
 * @Version 1.0
 **/
@Configuration
@EnableJms
public class ActiveMQConfiguration {
    /**
     * 方法描述 TODO 定义点对点队列
     *
     * @param
     * @return javax.jms.Queue
     * @author xiongfk
     * @date 2019/7/20
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("activeMq.queue");
    }

    /**
     * 方法描述 TODO 定义主题队列
     *
     * @param
     * @return javax.jms.Topic
     * @author xiongfk
     * @date 2019/7/20
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("activeMq.topic");
    }
}