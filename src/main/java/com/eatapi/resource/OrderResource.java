package com.eatapi.resource;

import com.eatapi.model.Order;
import com.eatapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping(value = "/all")
    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Order> getById(@PathVariable("id") Integer id){
        return orderRepository.findById(id);
    }

    @PostMapping(value = "/load")
    public List<Order> persist(@RequestBody final Order order){
        orderRepository.save(order);
        return orderRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Order order){
        this.orderRepository.save(order);
    }
}
