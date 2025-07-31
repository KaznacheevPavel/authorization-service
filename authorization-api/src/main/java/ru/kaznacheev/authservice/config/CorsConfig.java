package ru.kaznacheev.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

    private final ApplicationProperties.FrontendProperties frontendProperties;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(frontendProperties.getBaseUrl()));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Content-Type", "X-CSRF-TOKEN"));
        corsConfiguration.setExposedHeaders(List.of("Location"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
