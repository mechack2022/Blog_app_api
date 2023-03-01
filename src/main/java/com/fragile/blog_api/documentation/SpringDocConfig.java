package com.fragile.blog_api.documentation;

import com.fragile.blog_api.payloads.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.AbstractDocument;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Application API")
                        .description("This Application manages blogs post, comments and users activities")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .email("taiwogboyegun@gmail.com")
                                .name("Gboyegun Taiwo")
                                .url("https://www.linkedin.com/in/zurum-ogbonda")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                );
    }

//    ApiResponse badRequest = new ApiResponse().content(
//            new Content()
//    );

}
