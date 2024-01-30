package com.example.springbootsecurity.controller;

import com.example.springbootsecurity.Entity.User;
import com.example.springbootsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/user/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("test login", HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addPerson(user);
        return ResponseEntity.ok("User Added successfully!");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.getPersonById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search/user")
    public ResponseEntity<User> searchUser(@RequestParam String name){
        User user = userService.getPersonUsername(name);
        return ResponseEntity.ok(user);
    }
}
