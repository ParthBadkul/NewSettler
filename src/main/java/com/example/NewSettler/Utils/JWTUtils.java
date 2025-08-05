package com.example.NewSettler.Utils;

import com.example.NewSettler.DTO.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTUtils {

    @Value("${secret.key}")
    private static String key;



    public static String generateJWTToken(UserDto userDto) {

        if(userDto == null){

            return  "USer Cannot be null : Bad request";

        }
        else{


            Map<String, Object> claims = new HashMap<>();

            claims.put("userName" , userDto.getUserName());
            claims.put("roles", "ROLE_"+userDto.getRole());

            String kepo = "7wdjksjdkenkjahdkjhkjdehk88888888899989979798697877wdjksjdkenkjahdkjhkjdehk88888888899989979798697877wdjksjdkenkjahdkjhkjdehk8888888889998997979869787";


       return     Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDto.getUserName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                    .signWith(Keys.hmacShaKeyFor(kepo.getBytes()), SignatureAlgorithm.HS512)
                    .compact();


        }


    }


}
