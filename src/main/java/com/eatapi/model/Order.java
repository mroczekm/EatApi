package com.eatapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "orderlist")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, insertable = false, updatable = false)
    private User user;

    private Date date;

    private String name;

    private String description;

    private String status;

    public Order() {
    }
}

