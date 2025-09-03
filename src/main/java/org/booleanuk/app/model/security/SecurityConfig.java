package org.booleanuk.app.model.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()  // [!note] Public available base URL
                        .anyRequest().authenticated()				// [!note] Everything else must be authenticated
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {
                            // You can configure JwtAuthenticationConverter or other JWT options here if needed
                        })
                )
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {

        return NimbusJwtDecoder.withJwkSetUri(
                "http://localhost:8080/realms/booleanuk-sso-challenge-day/protocol/openid-connect/certs" // [!note] Make sure this reflect the Keycloak setting
        ).build();
    }
}