package com.example.NewSettler.controller;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.Enums.Country;
import com.example.NewSettler.Enums.Language;
import com.example.NewSettler.Enums.NewsCategory;
import com.example.NewSettler.Enums.UserEnum;
import com.example.NewSettler.entities.Users;
import com.example.NewSettler.service.SignUpTokenServices;
import com.example.NewSettler.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SignUpTokenServices signUpTokenServices;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody  @Validated  UserDto userDto){

        if(null != userDto){


            Users user1 = new Users();
            user1.setUserName(userDto.getUserName());
            user1.setPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user1.setActive(false);
            user1.setRole(UserEnum.CONTRIBUTOR);
            user1.setFirstName(userDto.getFirstName());
            user1.setLastName(userDto.getLastName());
            user1.setCountry(userDto.getCountry()!=null ? userDto.getCountry() : Country.us);
            user1.setLanguage(userDto.getLanguage()!=null ? userDto.getLanguage() : Language.en);
            user1.setFavnewsCategory(userDto.getNewsCategory() != null ? userDto.getNewsCategory() : NewsCategory.general);
            usersService.createUser(user1);
            String signup =  usersService.generateSignUpToken(user1);

        return ResponseEntity.ok(signup);

        }



    return ResponseEntity.badRequest().build();

    }

    @PostMapping("/verify")
    public  ResponseEntity<String> verify(@RequestParam String token ){
        if(token == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        else{

           if( signUpTokenServices.verfifyToken(token)){
               return ResponseEntity.ok("you are verifed");
           };


        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/signIn")

    public  ResponseEntity<String> signIn(@RequestBody @Validated UserDto userDto){


        try {
            String jwtToken = usersService.signIn(userDto);
            return ResponseEntity.ok(jwtToken);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        catch (RuntimeException e){

            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }
        catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("someThing went Wrong");
        }




    }

    @PutMapping("/changePreference")
    public ResponseEntity<String> changePreference(@RequestParam(required = false) @Validated Country country,
                                                 @RequestParam(required = false) @Validated Language language,
                                                 @RequestParam(required = false) @Validated NewsCategory newsCategory,Authentication authentication){

        UserDto userDto = new UserDto();
        userDto.setUserName(authentication.getName());
        userDto.setCountry(country);
        userDto.setLanguage(language);
        userDto.setNewsCategory(newsCategory);

       try {
           usersService.updatePreference(userDto);
           return ResponseEntity.ok("Prefernce changed ");
       }
       catch (Exception e){


           return ResponseEntity.badRequest().build();
       }


    }






}
