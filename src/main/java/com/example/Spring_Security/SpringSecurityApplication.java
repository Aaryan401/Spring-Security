package com.example.Spring_Security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management for Task",
				version= "2.03",
				description="understanding of swagger in Spring boot.",
				license = @License(name= "unknown 2.0",
						url="http://localhost:8080/swagger-ui/index.html#/"),
				contact = @Contact(name="<NAME>", email="<EMAIL>", url="https://github.com/souravdas007")
		)
)


@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
