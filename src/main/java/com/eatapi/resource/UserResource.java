package com.eatapi.resource;

import com.eatapi.model.User;
import com.eatapi.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserRepositry userRepositry;

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return this.userRepositry.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getById(@PathVariable("id") Integer id){
        return this.userRepositry.findById(id);
    }
}
