package com.eatapi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "user_roles")
public class UserRoles {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username_id")
    private int userId;

    private String username;

    private String role;

    public UserRoles(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
