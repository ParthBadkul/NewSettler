package com.example.NewSettler.DTO;

import com.example.NewSettler.Enums.Country;
import com.example.NewSettler.Enums.Language;
import com.example.NewSettler.Enums.NewsCategory;
import com.example.NewSettler.Enums.UserEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Cant Be empty String")
    @Email(message = "Enter Valid Email")
    private String userName;

    @NotBlank(message = "Password Cant Be Null")
    private String password;

    private UserEnum role;

    private Country country;

    private Language language;

    private NewsCategory newsCategory;

    public UserDto(String userName, String password, UserEnum role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserDto() {
    }

    public UserDto(String userName, String password, UserEnum role, Country country, Language language, NewsCategory newsCategory) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.country = country;
        this.language = language;
        this.newsCategory = newsCategory;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEnum getRole() {
        return role;
    }

    public void setRole(UserEnum role) {
        this.role = role;
    }
}
