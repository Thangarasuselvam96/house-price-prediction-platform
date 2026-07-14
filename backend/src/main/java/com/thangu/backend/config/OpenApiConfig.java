package com.thangu.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI housePriceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("House Price Prediction API")
                        .description("APIs for managing property listing and house price prediction")
                        .version("v1.0.0")
                );
    }
}
