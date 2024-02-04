package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.user.UserRequestDTO;
import com.kdu.smarthome.dto.user.UserResponseDTO;
import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.mapper.UserMapping;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.Utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapping userMapping,
                       JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register a user and return its JWT token
     * @param userRequestDTO
     * @return
     */
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO){
        UserModel userModel = userMapping.userMapping(userRequestDTO);
        userRepository.save(userModel);
        String token = jwtUtil.getTokenNew(userRequestDTO);
        return new UserResponseDTO("User added successfully",token);
    }

}
