package com.example.bikash.Social.Media.Configuration;

import com.example.bikash.Social.Media.Configuration.JWT.JwtRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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

                        .requestMatchers("/api/**", "user/register").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers("/comments/**").authenticated()
                        .requestMatchers("/reels/**").authenticated()
                        .requestMatchers("/stories/**").authenticated()
                        .requestMatchers("/chat/**").authenticated()
                        .requestMatchers("/message/**").authenticated()


                )


                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();


    }

    private CorsConfigurationSource corsConfigurationSource() {


        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {


                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Arrays.asList(



                        //  we have to add frontedn urls over here
                        " http://localhost:9000/"
                ));

                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(4600L);

                configuration.setExposedHeaders(Arrays.asList(
                        "Authorization"
                ));


                return configuration;
            }
        };
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
