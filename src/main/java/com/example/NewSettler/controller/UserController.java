package com.example.NewSettler.controller;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.Enums.UserEnum;
import com.example.NewSettler.entities.Users;
import com.example.NewSettler.service.SignUpTokenServices;
import com.example.NewSettler.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SignUpTokenServices signUpToken;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signUp")
    public String signUp(@RequestBody  @Validated  UserDto userDto){

        if(null != userDto){


            Users user1 = new Users();
            user1.setUserName(userDto.getUserName());
            user1.setPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user1.setActive(false);
            user1.setRole(UserEnum.CONTRIBUTOR);
         String signup =  usersService.generateSignUpToken(user1);
         usersService.createUser();



        }



    return "Invalid Request";

    }


}
