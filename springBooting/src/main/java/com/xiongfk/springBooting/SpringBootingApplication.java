package com.xiongfk.springBooting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
//@MapperScan("com.example.xiongfk.mapper")//将项目中对应的mapper类的路径加进来就可以了
@EnableJms
@EnableScheduling
public class SpringBootingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootingApplication.class, args);
    }
}
