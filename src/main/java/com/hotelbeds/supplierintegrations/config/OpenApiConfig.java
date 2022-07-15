package com.hotelbeds.supplierintegrations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * The Class OpenApiConfig.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Hacker Detector API").description(
                "This is a sample Foobar server created using springdocs - a library for OpenAPI 3 with spring boot.")
                .termsOfService("http://swagger.io/terms/"));
    }
}
