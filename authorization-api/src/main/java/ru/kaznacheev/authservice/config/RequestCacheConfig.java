package ru.kaznacheev.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class RequestCacheConfig {

    @Bean
    public HttpSessionRequestCache requestCache() {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        return requestCache;
    }

}
