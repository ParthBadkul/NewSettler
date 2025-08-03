package com.example.NewSettler.service;

import com.example.NewSettler.Repo.SignUpTokenRepo;
import com.example.NewSettler.Repo.UserRepo;
import com.example.NewSettler.entities.SignUpToken;
import com.example.NewSettler.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpTokenServices {


    @Autowired
    private SignUpTokenRepo signUpTokenRepo;

    @Autowired
   private UserRepo userRepo;



    public Boolean verfifyToken(String tok){

        SignUpToken token = signUpTokenRepo.findByToken(tok);
        if(token != null){
            Users user = token.getUser();

            if(user == null){
                return false;
            }

            user.setActive(true);
            userRepo.save(user);
            signUpTokenRepo.delete(token);
            return true;

        }



        return false;
    }


}
