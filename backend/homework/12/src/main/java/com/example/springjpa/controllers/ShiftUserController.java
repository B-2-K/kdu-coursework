package com.example.springjpa.controllers;

import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.services.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/shift-users")
public class ShiftUserController {

    @Autowired
    private ShiftUserService shiftUserService;

    @GetMapping
    public List<ShiftUser> getAllShiftUsers() {
        return shiftUserService.getAllShiftUsers();
    }

    @GetMapping("/{id}")
    public Optional<ShiftUser> getShiftUserById(@PathVariable UUID id) {
        return shiftUserService.getShiftUserById(id);
    }

    @PostMapping
    public ShiftUser saveShiftUser(@RequestBody ShiftUser shiftUser) {
        return shiftUserService.saveShiftUser(shiftUser);
    }

    @DeleteMapping("/delete-by-shift-end-time/{id}")
    public ResponseEntity<String> deleteShiftUserByShiftEndTime(@PathVariable UUID id) {
        List<ShiftUser> shiftUsers = shiftUserService.getShiftUsersByShiftEndTime(LocalTime.of(23, 0));

        if (shiftUsers.isEmpty()) {
            return new ResponseEntity<>("No ShiftUser found for the specified shift end time", HttpStatus.NOT_FOUND);
        }

        boolean userExists = shiftUsers.stream().anyMatch(user -> user.getId().equals(id));

        if (userExists) {
            shiftUserService.deleteShiftUser(id);
            return new ResponseEntity<>("ShiftUser deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ShiftUser not found for the specified shift end time", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteShiftUser(@PathVariable UUID id) {
        shiftUserService.deleteShiftUser(id);
    }
}
