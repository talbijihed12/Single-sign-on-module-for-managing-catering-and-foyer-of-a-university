package com.example.ssoeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer

@SpringBootApplication
public class SsoEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoEurekaServerApplication.class, args);
    }

}
