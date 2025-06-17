package com.example.Spring_Security.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI setUpOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Sring Security and Employee Management")
                .description("This is an application to understand Spring Security with Employee Management")
                .version("1.0")
                .contact(new Contact()
                        .name("Signimus")
                        .email("aaryan09@example.com")
                        .url("https://signimus.com/"))
                .license(new License()
                        .name("This is my licence")
                        .url("https://signimus.com/"))
                .summary("This is created for the understanding of Spring Security and employee management" +
                        " application and we have explored every part of the application and still we are on the way")

        );
    }
}
