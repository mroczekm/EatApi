package com.eatapi.repository;

import com.eatapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}


