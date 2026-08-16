package com.project.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
        public JwtDecoder jwtDecoder() {
            return NimbusJwtDecoder.withJwkSetUri("https://mpxrwjinrfllygysbyei.supabase.co/auth/v1/.well-known/jwks.json")
                    .build();
        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // without this cors call the preflight request gets a 401 before the jwt is even checked
        http.cors(Customizer.withDefaults())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/public/**")
        .permitAll().anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }
}
