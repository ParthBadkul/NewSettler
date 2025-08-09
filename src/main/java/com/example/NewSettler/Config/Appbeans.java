package com.example.NewSettler.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Appbeans {

    @Bean
    public WebClient webClientBean(){

        return WebClient.builder().build();
    }

}
