package com.eatapi.resource;

import com.eatapi.model.User;
import com.eatapi.model.UserRoles;
import com.eatapi.repository.UserRepositry;
import com.eatapi.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserRepositry userRepositry;

    @Autowired
    UserRolesRepository userRolesRepository;

    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public boolean validateLogin() {
        return true;
    }

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return this.userRepositry.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getById(@PathVariable("id") Integer id){
        return this.userRepositry.findById(id);
    }

    @GetMapping(value = "/byUserName/{username}")
    public Optional<User> getByUsername(@PathVariable("username")String username){
        return this.userRepositry.findByUsername(username);
    }

    @PostMapping(value = "/add")
    public void save(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.userRepositry.save(user);
        UserRoles userRoles = new UserRoles(user.getId(), user.getUsername(), "ROLE_USER");
        this.userRolesRepository.save(userRoles);
    }
}
