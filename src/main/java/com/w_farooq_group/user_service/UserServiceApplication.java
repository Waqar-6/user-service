package com.w_farooq_group.user_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Micro Service for User Service",
				description = "REST API documentation for user service",
				version = "v1",
				contact = @Contact(
						name = "Waqar Farooq",
						email = "waqar-1@hotmail.com"
//						url = "need to complete this"
				)
		)
)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
