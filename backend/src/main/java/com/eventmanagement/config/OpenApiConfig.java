package com.eventmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Event Management System API",
        version = "1.0",
        description = "Backend API for the Event Management System",
        contact = @Contact(name = "Rabeya Noor", url = "https://github.com/rabeyanoor")
    )
)
public class OpenApiConfig {} 