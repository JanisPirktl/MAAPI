package net.ictcampus.martialartapi.controller.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                //fügt das Securityrequirement Bearer Authentication hinzu
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                //erstellt das entsprechende Schema
                .info(new Info().title("MaAPI")
                        .description("Martial Art API Projekt")
                        .version("1.0"));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
        //Funktion zum erstellen des Schemas, setzt den Typ auf HTTP und sagt welches Token
        //als Bearer verwendet wird
    }

}

