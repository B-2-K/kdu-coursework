package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.request.UserRequestDTO;
import com.kdu.smarthome.dto.response.UserResponseDTO;
import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.mapping.Mapper;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.security.filters.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for user-related operations, including registration and token generation.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final TokenStore tokenStore;

    @Autowired
    public UserService(UserRepository userRepository, Mapper mapper, TokenStore tokenStore) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.tokenStore = tokenStore;
    }

    /**
     * Registers a user and returns the associated JWT token.
     *
     * @param userRequestDTO The DTO containing user registration details.
     * @return The response containing information about the successful user registration and the JWT token.
     */
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        UserModel userModel = mapper.userMapping(userRequestDTO);
        userRepository.save(userModel);
        return new UserResponseDTO("User added successfully", tokenStore.getToken(userRequestDTO));
    }
}
