package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.user.UserRequestDTO;
import com.kdu.smarthome.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserModel userMapping(UserRequestDTO userRequestDTO){
        UserModel userModel = new UserModel();
        userModel.setUsername(userRequestDTO.getUsername());
        userModel.setFirstName(userRequestDTO.getFirstName());
        userModel.setLastName(userRequestDTO.getLastName());
        userModel.setEmail(userRequestDTO.getEmail());
        userModel.setName(userRequestDTO.getName());
        userModel.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return userModel;
    }
}
