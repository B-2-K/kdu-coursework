package com.example.springtest.config;

import com.example.springtest.filter.TokenGeneratorFilter;
import com.example.springtest.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {

    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/person/login").permitAll()
                        .requestMatchers("/products", "/products/**", "/products/*").hasRole("ADMIN")
                        .anyRequest().authenticated()).csrf().disable();
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
