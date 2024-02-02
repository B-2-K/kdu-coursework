package com.example.springtest.controller;

import com.example.springtest.entities.UserJPA;
import com.example.springtest.service.UserJPAServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allusers")
public class UserJPAController {
    @Autowired
    private UserJPAServices userJPAServices;

    @GetMapping
    public List<UserJPA> getAll(){
        return userJPAServices.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserJPA> createProduct(@RequestBody UserJPA user){
        userJPAServices.saveUserJPA(user);
        return new ResponseEntity<UserJPA>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserJPA> updateUser(@PathVariable Long userId, @RequestBody UserJPA updatedUser) {
        UserJPA existingUser = userJPAServices.getUserById(userId);

        if (existingUser != null) {
            // Update the existing user with the new values
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());

            // Save the updated user
            userJPAServices.saveUserJPA(existingUser);

            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/{quantity}")
    public void buyProduct(@PathVariable int id, @PathVariable int quantity){
        userJPAServices.buyProductById(id, quantity);
    }
}
