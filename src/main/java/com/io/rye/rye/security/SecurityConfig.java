package com.io.rye.rye.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.token.key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTokenFilter(key), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers("admin/login").permitAll()
                                        .requestMatchers("guardian/login").permitAll()
                                        .requestMatchers("kid/login").permitAll()
                                        .requestMatchers("guardian/register").permitAll()
                                        .requestMatchers("kid/register").permitAll()
                                        .requestMatchers("pic/**").authenticated()
                                        .requestMatchers("emotion/**").authenticated()
                                        .requestMatchers("result/add").permitAll()
                                        .requestMatchers("result/getAll").permitAll()
                                        .requestMatchers("aws/extractEmotion").hasRole("KID")
                                        .requestMatchers("stats/**").permitAll()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
