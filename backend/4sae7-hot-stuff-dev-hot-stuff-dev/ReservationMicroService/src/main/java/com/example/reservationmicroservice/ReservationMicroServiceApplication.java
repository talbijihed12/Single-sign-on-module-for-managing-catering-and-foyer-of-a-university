package com.example.reservationmicroservice;

import com.example.reservationmicroservice.Controllers.ReservationFoyerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebSecurity
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication

//@ComponentScan(basePackageClasses = ReservationFoyerController.class)
public class ReservationMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationMicroServiceApplication.class, args);
    }

}
