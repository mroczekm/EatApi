package com.eatapi.repository;

import com.eatapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User, Integer> {
}
