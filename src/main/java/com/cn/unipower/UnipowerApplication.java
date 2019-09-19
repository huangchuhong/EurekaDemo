package com.cn.unipower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UnipowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnipowerApplication.class, args);
        System.out.println("EurekaDemo");
    }

}
