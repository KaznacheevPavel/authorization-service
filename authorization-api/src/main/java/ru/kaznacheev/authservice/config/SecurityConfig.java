package ru.kaznacheev.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.cors.CorsConfigurationSource;
import ru.kaznacheev.authservice.config.handler.CustomAuthenticationFailureHandler;
import ru.kaznacheev.authservice.config.handler.CustomAuthenticationSuccessHandler;
import ru.kaznacheev.authservice.service.impl.CustomOauth2UserService;
import ru.kaznacheev.authservice.service.impl.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final HttpSessionRequestCache requestCache;
    private final CorsConfigurationSource corsConfigurationSource;
    private final SynchronizedCsrfTokenRepository synchronizedCsrfTokenRepository;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOauth2UserService oAuth2UserService;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestCache(cacheConfigurer ->
                        cacheConfigurer.requestCache(requestCache))

                .cors(corsConfigurer ->
                        corsConfigurer.configurationSource(corsConfigurationSource))

                .csrf(csrfConfigurer ->
                        csrfConfigurer.csrfTokenRepository(synchronizedCsrfTokenRepository))

                .oauth2Login(oauth2Loginconfigurer ->
                        oauth2Loginconfigurer.userInfoEndpoint(userInfoEndpointConfigurer ->
                                userInfoEndpointConfigurer.userService(oAuth2UserService)))

                .authorizeHttpRequests(requestConfigurer -> {
                    requestConfigurer.requestMatchers(HttpMethod.GET, "/csrf").permitAll();
                    requestConfigurer.anyRequest().authenticated();
                })

                .formLogin(formLoginConfigurer -> {
                    formLoginConfigurer.loginPage("http://localhost:3000/auth/login");
                    formLoginConfigurer.loginProcessingUrl("/login");
                    formLoginConfigurer.successHandler(successHandler);
                    formLoginConfigurer.failureHandler(failureHandler);
                });

        httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .parentAuthenticationManager(null)
                .userDetailsService(userDetailsService);

        return httpSecurity.build();
    }

}
