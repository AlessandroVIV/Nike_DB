package com.project.Nike_DB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disabilita CSRF per Postman e test
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").permitAll() // rotte pubbliche
                        .anyRequest().authenticated() // tutte le altre richiedono login
                );

        return http.build(); // build finale della security chain
    }
}
