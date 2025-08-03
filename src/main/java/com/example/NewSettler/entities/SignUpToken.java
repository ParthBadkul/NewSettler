package com.example.NewSettler.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity

public class SignUpToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String token;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Users users;

    public SignUpToken() {
    }

    public SignUpToken(long id, String token, Users users) {
        this.id = id;
        this.token = token;
        this.users = users;
    }


    public long getId() {
        return id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
