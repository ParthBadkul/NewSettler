package com.example.NewSettler.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/articles")
    @PreAuthorize("hasRole('CONTRIBUTOR')")
    public String getNewsArtile(@RequestParam(defaultValue = "hello") String newsChannel){


        return "newsChannel";
    }


}
