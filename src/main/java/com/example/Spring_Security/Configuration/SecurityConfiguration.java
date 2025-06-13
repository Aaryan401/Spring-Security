package com.example.Spring_Security.Configuration;


import com.example.Spring_Security.JwtFilters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    //Injecting custom authentication provider for handling user credentials.
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    //injecting custom jwt filet to intercept request and valid tokens.
    @Autowired
    private final JwtFilter jwtFilter;

    /**
     * 1.remember to add "/" in the beginning of the prefix API(.requestMatchers)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/v1/**")
                        .permitAll()    //By giving permitAll i.e. The method within that URL(URLand Prefix written in requestMatchers) is accessible to all
                        .requestMatchers("/api/admin/v1/**").hasRole("ADMIN")
                        .requestMatchers("/api/employee/v1/**").hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers("/api/manager/v1/**").hasRole("MANAGER")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}