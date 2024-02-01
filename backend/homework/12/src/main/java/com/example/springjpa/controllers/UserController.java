package com.example.springjpa.controllers;

import com.example.springjpa.entities.User;
import com.example.springjpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update-details/{userId}")
    public ResponseEntity<String> updateUserDetails(
            @PathVariable UUID userId,
            @RequestParam String username,
            @RequestParam short loggedIn,
            @RequestParam String timeZone) {

        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            userService.updateUserDetails(userId, username, loggedIn, timeZone);
            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found for the specified UUID", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginated")
    public Page<User> findAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return userService.findAllUsersPaginated(page, size);
    }
}
