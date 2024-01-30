package com.toki.backend.config;

import com.toki.backend.utils.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Value("#{'${toki.config.cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigins;

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(allowedOrigins);
        config.addAllowedOrigin("localhost:5173");
        config.setAllowedMethods(List.of("GET", "POST", "PUT","DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.addExposedHeader(TokenProvider.AUTHORIZATION_HEADER);
        config.addExposedHeader("Content-Disposition");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}