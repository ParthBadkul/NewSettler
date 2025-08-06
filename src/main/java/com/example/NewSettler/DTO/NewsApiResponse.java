package com.example.NewSettler.DTO;

import java.util.List;

public class NewsApiResponse {

        private String status;
        private int totalResults;
        private List<NewsApiDTO> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsApiDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsApiDTO> articles) {
        this.articles = articles;
    }

    // Getters and Setters
    }

