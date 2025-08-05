package com.example.NewSettler.controller;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.Enums.UserEnum;
import com.example.NewSettler.entities.Users;
import com.example.NewSettler.service.SignUpTokenServices;
import com.example.NewSettler.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SignUpTokenServices signUpTokenServices;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody  @Validated  UserDto userDto){

        if(null != userDto){


            Users user1 = new Users();
            user1.setUserName(userDto.getUserName());
            user1.setPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user1.setActive(false);
            user1.setRole(UserEnum.CONTRIBUTOR);
            usersService.createUser(user1);
            String signup =  usersService.generateSignUpToken(user1);

        return ResponseEntity.ok(signup);

        }



    return ResponseEntity.badRequest().build();

    }

    @PostMapping("/verify")
    public  ResponseEntity<String> verify(@RequestParam String token ){
        if(token == null){
            return  ResponseEntity.badRequest().build();
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




}
