package com.w_farooq_group.user_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "REST API FOR USER SERVICE",
				description = "CRUD REST API for users",
				version = "v1",
				contact = @Contact(
						name = "Waqar Farooq",
						email = "farooqwaqar18@gmail.com",
						url = "www.wfarooqgroup.com"
				)
		)
)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
