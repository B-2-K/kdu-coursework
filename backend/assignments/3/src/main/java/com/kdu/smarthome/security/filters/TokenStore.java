package com.kdu.smarthome.security.filters;

import com.kdu.smarthome.security.config.CustomAuthenticationManager;
import com.kdu.smarthome.dto.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * A component responsible for handling authentication and generating JWT tokens for users.
 */
@Component
public class TokenStore {

    private final CustomAuthenticationManager customAuthProvider;
    private final TokenGeneratorFilter tokenGeneratorFilter;

    /**
     * Constructor for TokenStore.
     *
     * @param customAuthProvider   The custom authentication manager for user authentication.
     * @param tokenGeneratorFilter The filter for generating JWT tokens.
     */
    @Autowired
    public TokenStore(CustomAuthenticationManager customAuthProvider,
                      TokenGeneratorFilter tokenGeneratorFilter) {
        this.customAuthProvider = customAuthProvider;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
    }

    /**
     * Retrieves a new JWT token for the provided user credentials.
     *
     * @param userRequestDTO The user request DTO containing username and password.
     * @return The generated JWT token as a string.
     */
    public String getToken(UserRequestDTO userRequestDTO) {
        Authentication authentication = customAuthProvider.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword())
        );
        return tokenGeneratorFilter.generateJWT(authentication);
    }
}
