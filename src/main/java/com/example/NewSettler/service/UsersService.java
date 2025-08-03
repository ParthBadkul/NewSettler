package com.example.NewSettler.service;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.Repo.SignUpTokenRepo;
import com.example.NewSettler.Repo.UserRepo;
import com.example.NewSettler.entities.SignUpToken;
import com.example.NewSettler.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class UsersService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SignUpTokenRepo signUpTokenRepo;

    @Autowired
    private UserRepo userRepo;

    public String generateSignUpToken(Users user) {

        String token = UUID.randomUUID().toString();

        SignUpToken signUpToken = new SignUpToken();
        signUpToken.setToken(token);
        signUpToken.setUser(user);

        signUpTokenRepo.save(signUpToken);

        return "Click to link to verify " + "http://localhost:8080/verify?token="+ signUpToken;
    }

    public void createUser() {


    }
}
