package com.eatapi.resource;

import com.eatapi.model.OrderDetails;
import com.eatapi.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/orderDetails")
public class OrderDetailsResource {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @PostMapping
    public void save(@RequestBody OrderDetails orderDetails){
        this.orderDetailsRepository.save(orderDetails);
    }

    @GetMapping(value = "/getByOrderId/{id}")
    public List<OrderDetails> getByOrderId(@PathVariable("id") int id){
        return this.orderDetailsRepository.findByOrderId(id);

    }
}
