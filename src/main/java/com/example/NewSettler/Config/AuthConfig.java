package com.example.NewSettler.Config;


import com.example.NewSettler.Config.Filters.JWTfilter;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class AuthConfig {



    @Bean
    public BCryptPasswordEncoder customEncoder(){

        return  new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {

        return https.csrf( csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.POST, "/signUp","/verify","/signIn")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/news").authenticated()
                                .anyRequest()
                                .authenticated()


                ).addFilterBefore(new JWTfilter() , UsernamePasswordAuthenticationFilter.class)
                .build();

    }



}
