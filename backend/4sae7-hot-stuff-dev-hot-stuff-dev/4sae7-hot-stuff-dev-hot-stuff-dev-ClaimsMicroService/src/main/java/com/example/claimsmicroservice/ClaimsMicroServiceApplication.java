package com.example.claimsmicroservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class ClaimsMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimsMicroServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
