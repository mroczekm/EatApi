package com.eatapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "orderdetails")
public class OrderDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, insertable = false, updatable = false)
    private User user;

    private double price;

    private String description;

    private double extra;

    private String status;

    public OrderDetails() {
    }
}
