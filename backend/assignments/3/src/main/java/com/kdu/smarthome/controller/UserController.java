package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.user.UserRequestDTO;
import com.kdu.smarthome.dto.user.UserResponseDTO;
import com.kdu.smarthome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a user using valid credentials and return token
     * @param userRequestDTO
     * @return
     */
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
}
