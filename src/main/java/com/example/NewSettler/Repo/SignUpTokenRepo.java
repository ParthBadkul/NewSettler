package com.example.NewSettler.Repo;

import com.example.NewSettler.entities.SignUpToken;
import com.example.NewSettler.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()

public interface SignUpTokenRepo extends JpaRepository<SignUpToken , Long> {


    SignUpToken findByToken(String token);

}
