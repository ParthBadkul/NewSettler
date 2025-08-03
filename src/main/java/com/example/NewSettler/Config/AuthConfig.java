package com.example.NewSettler.Config;


import jakarta.servlet.http.HttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class AuthConfig {

    @Bean
    public BCryptPasswordEncoder customEncoder(){

        return  new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {

        return https.csrf( csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/signUp","/verify")
                        .permitAll().anyRequest().authenticated()

                ).build();

    }



}
