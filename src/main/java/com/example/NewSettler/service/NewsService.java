package com.example.NewSettler.service;

import com.example.NewSettler.DTO.NewsApiDTO;
import com.example.NewSettler.DTO.NewsApiResponse;
import com.example.NewSettler.Repo.UserRepo;
import com.example.NewSettler.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private UserRepo userRepo;

    public List<NewsApiDTO>getDefaultNews(String userName){

        Users user = userRepo.findByUserName(userName);

        if(user == null){

            return null;
        }


        String defCountry = (user.getCountry() != null)? user.getCountry().toString().toLowerCase() : null;

        String language = (user.getLanguage() != null) ? user.getLanguage().toString().toLowerCase() : null;

        String newsCategory = (user.getCountry() !=null) ? user.getCountry().toString() :null;

        String apiKey = "54974791668e414ba5024b4d0950c2ec";


        StringBuilder url = new StringBuilder("https://newsapi.org/v2/top-headlines?");
        boolean hasParams = false;

        if (defCountry != null) {
            url.append("country=").append(defCountry);
            hasParams = true;
        }

        if (newsCategory != null) {
            if (hasParams) url.append("&");
            url.append("category=").append(newsCategory);
            hasParams = true;
        }

        if (language != null) {
            if (hasParams) url.append("&");
            url.append("language=").append(language);
            hasParams = true;
        }


        if (!hasParams) {
            url.append("country=us");
        }

        // Prepare request
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-api-key", apiKey);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<NewsApiResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity,
                NewsApiResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getArticles();
        }

        return null;


    }

}
