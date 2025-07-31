package ru.kaznacheev.authservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Configuration
    @ConfigurationProperties(prefix = "frontend")
    @Getter
    @Setter
    public static class FrontendProperties {

        private String baseUrl;
        private String loginPage;

    }

}
