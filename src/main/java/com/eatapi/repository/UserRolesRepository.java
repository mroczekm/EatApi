package com.eatapi.repository;

import com.eatapi.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
}
