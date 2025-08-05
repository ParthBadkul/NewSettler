package com.example.NewSettler.Repo;

import com.example.NewSettler.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    public Users findByUserName(String userName);

}
