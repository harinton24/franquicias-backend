package com.accenture.franquicias.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Franquicias - Accenture")
                        .version("1.0")
                        .description("Prueba técnica para el manejo de franquicias, sucursales y productos usando Spring Boot WebFlux y R2DBC."));
    }
}