package com.example.bikash.Social.Media.Configuration;

import com.example.bikash.Social.Media.Configuration.JWT.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest

                        .requestMatchers("/api/**","user/register").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers("/comments/**").authenticated()


                )


                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}
