package com.example.authentificationmicroservice;

import com.example.authentificationmicroservice.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Date;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class AuthentificationMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationMicroServiceApplication.class, args);
	}

}
