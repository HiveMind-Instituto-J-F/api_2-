package com.api_rest.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsSecurity {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**") // 1. Applies this policy to ALL API paths
                        .allowedOrigins("http://localhost:5173") // 2. ALLOWS requests ONLY from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. ALLOWS these HTTP methods
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
