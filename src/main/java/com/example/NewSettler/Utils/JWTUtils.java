package com.example.NewSettler.Utils;

import com.example.NewSettler.DTO.UserDto;
import com.example.NewSettler.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class JWTUtils {


   static  String kepo = "7wdjksjdkenkjahdkjhkjdehk88888888899989979798697877wdjksjdkenkjahdkjhkjdehk88888888899989979798697877wdjksjdkenkjahdkjhkjdehk8888888889998997979869787";




    public static String generateJWTToken(Users userDto) {

        if(userDto == null){

            return  "USer Cannot be null : Bad request";

        }
        else{


            Map<String, Object> claims = new HashMap<>();

            claims.put("userName" , userDto.getUserName());
            claims.put("roles", "ROLE_"+userDto.getRole());



       return     Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDto.getUserName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                    .signWith(Keys.hmacShaKeyFor(kepo.getBytes()), SignatureAlgorithm.HS512)
                    .compact();


        }


    }


    public static Claims isValidToken(String token){

       try {
          return Jwts.parserBuilder()
                   .setSigningKey((Keys.hmacShaKeyFor(kepo.getBytes(StandardCharsets.UTF_8))))
                   .build()
                   .parseClaimsJws(token)
                   .getBody();

       }
       catch (Exception e){

           return null;

       }





    }


}
