package com.example.NewSettler.entities;

import com.example.NewSettler.Enums.UserEnum;
import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isActive;

    private String userName;

    private String passWord;

    @Enumerated(EnumType.STRING)
    private UserEnum role;

    public Users() {
    }

    public Users(long id, boolean isActive, String userName, String passWord, UserEnum role) {
        this.id = id;
        this.isActive = isActive;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public long getId() {
        return id;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserEnum getRole() {
        return role;
    }

    public void setRole(UserEnum role) {
        this.role = role;
    }
}
