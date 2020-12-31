package com.xiongfk.springBooAdmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringBooAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBooAdminApplication.class, args);
    }

}
