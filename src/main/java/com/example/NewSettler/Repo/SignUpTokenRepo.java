package com.example.NewSettler.Repo;

import com.example.NewSettler.entities.SignUpToken;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()

public interface SignUpTokenRepo extends JpaRepository<SignUpToken , Long> {
}
