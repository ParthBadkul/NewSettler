package com.example.NewSettler.service;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.Repo.SignUpTokenRepo;
import com.example.NewSettler.Repo.UserRepo;
import com.example.NewSettler.Utils.JWTUtils;
import com.example.NewSettler.entities.SignUpToken;
import com.example.NewSettler.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

        return "Click to link to verify " + "http://localhost:8080/verify?token="+ signUpToken.getToken();
    }

    public void createUser( @Validated Users user) {
            if(user!= null){

                userRepo.save(user);

            }

    }

    public String signIn(UserDto userDto) throws RuntimeException {

        if(userDto == null){

            throw new IllegalArgumentException("User data cannot be null");
        }

        Users user = userRepo.findByUserName(userDto.getUserName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }


       if( bCryptPasswordEncoder.matches(userDto.getPassword() , user.getPassWord()) && user.isActive()){

           String jwts = JWTUtils.generateJWTToken(user);
           return jwts;

       };

       throw new RuntimeException();




    }
}
