package com.eatapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "orderlist")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private String name;

    private String description;

    private String status;

    public Order() {
    }
}

