package com.paulonepotti.ecommerce.product_microservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Product Microservice API")
                .version("1.0.0")
                .description("API para gestionar productos en el sistema de comercio electrónico con microservicios.")
                .contact(new Contact()
                    .name("Ing. Paulo Nepotti")
                    .email("pnepotti@frlp.utn.edu.ar")
                    .url("https://github.com/pnepotti")));
    }
}

