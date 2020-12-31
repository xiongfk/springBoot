package com.xiongfk.springBooting.config;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/4/8
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig extends BaseCommonLog {
    public static final String DEFAULT_BOOK_QUEUE = "dev.book.register.default.queue";
    public static final String MANUAL_BOOK_QUEUE = "dev.book.register.manual.queue";

    @Value(value = "${spring.rabbitmq.username}")
    private String userName;
    @Value(value = "${spring.rabbitmq.password}")
    private String password;
    @Value(value = "${spring.rabbitmq.host}")
    private String host;
    @Value(value = "${spring.rabbitmq.port}")
    private Integer port;
    @Value(value = "${spring.rabbitmq.virtual-host}")
    private String virtualHost;

//    public Connection getConnection(){
//        // 通过工程获取连接
//        Connection connection = null;
//        try {
//            //定义连接工厂
//            ConnectionFactory factory = new ConnectionFactory();
//            //设置服务地址
//            factory.setHost(host);
//            //端口
//            factory.setPort(port);
//            //设置账号信息，用户名、密码、vhost
//            factory.setVirtualHost(virtualHost);
//            factory.setUsername(userName);
//            factory.setPassword(password);
//            connection = factory.newConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    @Bean
    public Queue defaultBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }
}

