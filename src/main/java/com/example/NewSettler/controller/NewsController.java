package com.example.NewSettler.controller;

import com.example.NewSettler.DTO.NewsApiDTO;
import com.example.NewSettler.DTO.NewsApiResponse;
import com.example.NewSettler.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/articlesSync")
    @PreAuthorize("hasRole('CONTRIBUTOR')")
    public Mono<NewsApiResponse> getNewsChannelOfMyRole(Authentication authentication){ // I am using Auth object because I am already sending claims in the auth object so why to you use db again

        String userId = authentication.getName();
       return newsService.getDefaultNews(userId);


    }


    @GetMapping("/articles")
    @PreAuthorize("hasRole('CONTRIBUTOR')")
    public List<NewsApiDTO> getNewsChannelOfMyRoleShnc(Authentication authentication){ // I am using Auth object because I am already sending claims in the auth object so why to you use db again

        String userId = authentication.getName();

        List<NewsApiDTO> newsApiDTO = newsService.getDefaultNewsSync(userId);

        return newsApiDTO;




    }



    @PostMapping("/articlesLogs")
    @PreAuthorize("hasRole('CONTRIBUTOR')")
    public Mono<NewsApiResponse> getNewsChannelOfMyRoleLogs(Authentication authentication) {
        String userId = authentication.getName();

        System.out.println("=== REACTIVE ENDPOINT ===");
        System.out.println("User: " + userId);
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Authorities: " + authentication.getAuthorities());

        return newsService.getDefaultNews(userId)
                .doOnSubscribe(subscription -> {
                    System.out.println("=== MONO SUBSCRIBED ===");
                    System.out.println("Subscription: " + subscription);
                })
                .doOnNext(articles -> {
                    System.out.println("=== MONO ON NEXT ===");
                    System.out.println("Articles: " + articles.size());
                })
                .doOnSuccess(articles -> {
                    System.out.println("=== MONO SUCCESS ===");
                    System.out.println("Articles: " + (articles != null ? articles.size() : "null"));
                })
                .doOnError(error -> {
                    System.out.println("=== MONO ERROR ===");
                    System.out.println("Error: " + error.getMessage());
                    error.printStackTrace();
                })
                .doFinally(signalType -> {
                    System.out.println("=== MONO FINALLY ===");
                    System.out.println("Signal: " + signalType);
                    System.out.println("Thread: " + Thread.currentThread().getName());
                });
    }


}



