package com.eatapi.resource;

import com.eatapi.EmailServiceImpl;
import com.eatapi.model.Order;
import com.eatapi.model.User;
import com.eatapi.repository.OrderDetailsRepository;
import com.eatapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @GetMapping(value = "/all")
    public List<Order> getAll(){
        return this.orderRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @GetMapping(value = "/{id}")
    public Optional<Order> getById(@PathVariable("id") Integer id){
        return orderRepository.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        orderRepository.deleteById(id);
    }

    @PostMapping(value = "/load")
    public List<Order> persist(@RequestBody final Order order){
        orderRepository.save(order);
        return orderRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Order order) {
        this.orderRepository.save(order);
        emailService.sendMails(order);
    }
}
