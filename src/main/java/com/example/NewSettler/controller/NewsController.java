package com.example.NewSettler.controller;

import com.example.NewSettler.DTO.NewsApiDTO;
import com.example.NewSettler.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/articles")
    @PreAuthorize("hasRole('CONTRIBUTOR')")
    public ResponseEntity<List<NewsApiDTO>> getNewsChannelOfMyRole(Authentication authentication){ // I am using Auth object because I am already sending claims in the auth object so why to you use db again

        String userId = authentication.getName();

       List<NewsApiDTO> newsApiDTO = newsService.getDefaultNews(userId);

        if(newsApiDTO != null){

            return ResponseEntity.ok(newsApiDTO);
        }else{

            return ResponseEntity.badRequest().build();
        }




    }


}
