package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.UserRequestDTO;
import com.kdu.smarthome.dto.response.UserResponseDTO;
import com.kdu.smarthome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling user authentication operations.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService The service responsible for user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for user registration.
     *
     * @param userRequestDTO The DTO containing information for user registration.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.addUser(userRequestDTO), HttpStatus.OK);
    }
}
