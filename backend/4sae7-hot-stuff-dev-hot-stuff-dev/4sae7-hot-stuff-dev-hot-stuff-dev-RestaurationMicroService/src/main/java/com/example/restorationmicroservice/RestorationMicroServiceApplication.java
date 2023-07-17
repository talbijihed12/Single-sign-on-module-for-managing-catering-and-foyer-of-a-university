package com.example.restorationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableScheduling
@EnableDiscoveryClient

public class RestorationMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestorationMicroServiceApplication.class, args);
    }

}
