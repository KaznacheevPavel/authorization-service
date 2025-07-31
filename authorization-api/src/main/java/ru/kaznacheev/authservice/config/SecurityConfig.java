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
import ru.kaznacheev.authservice.config.handler.DelegatedAccessDeniedHandler;
import ru.kaznacheev.authservice.config.handler.DelegatedAuthenticationFailureHandler;
import ru.kaznacheev.authservice.config.handler.DelegatedAuthenticationSuccessHandler;
import ru.kaznacheev.authservice.service.impl.CustomOauth2UserService;
import ru.kaznacheev.authservice.service.impl.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApplicationProperties.FrontendProperties frontendProperties;
    private final HttpSessionRequestCache requestCache;
    private final CorsConfigurationSource corsConfigurationSource;
    private final SynchronizedCsrfTokenRepository csrfTokenRepository;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOauth2UserService oAuth2UserService;
    private final DelegatedAuthenticationSuccessHandler authenticationSuccessHandler;
    private final DelegatedAuthenticationFailureHandler authenticationFailureHandler;
    private final DelegatedAccessDeniedHandler accessDeniedHandler;
    private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestCache(cacheConfigurer ->
                        cacheConfigurer.requestCache(requestCache))

                .cors(corsConfigurer ->
                        corsConfigurer.configurationSource(corsConfigurationSource))

                .csrf(csrfConfigurer ->
                        csrfConfigurer.csrfTokenRepository(csrfTokenRepository))

                .authorizeHttpRequests(authorizeHttpRequestConfigurer -> {
                    authorizeHttpRequestConfigurer
                            .requestMatchers(HttpMethod.GET, "/csrf").permitAll()
                            .anyRequest().authenticated();
                })

                .formLogin(formLoginConfigurer -> {
                    formLoginConfigurer
                            .loginProcessingUrl("/login")
                            .successHandler(authenticationSuccessHandler)
                            .failureHandler(authenticationFailureHandler);
                })

                .oauth2Login(oauth2Loginconfigurer -> {
                    oauth2Loginconfigurer
                            .defaultSuccessUrl(frontendProperties.getBaseUrl())
                            .failureHandler(authenticationFailureHandler)
                            .userInfoEndpoint(userInfoEndpointConfigurer ->
                                    userInfoEndpointConfigurer.userService(oAuth2UserService));
                })

                .exceptionHandling(exceptionHandlingConfigurer -> {
                    exceptionHandlingConfigurer
                            .authenticationEntryPoint(authenticationEntryPoint)
                            .accessDeniedHandler(accessDeniedHandler);
                });

        httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .parentAuthenticationManager(null)
                .userDetailsService(userDetailsService);

        return httpSecurity.build();
    }

}
