package com.kdu.smarthome.security.config;

import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Custom implementation of UserDetailsService for loading user details.
 */
@Component
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor for CustomUserDetails.
     *
     * @param userRepository The repository for accessing user data.
     */
    @Autowired
    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user details by username.
     *
     * @param username The username to load user details for.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException If user details are not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUserModel = userRepository.findByUsername(username);

        if (optionalUserModel.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for user: " + username + ". Please register first.");
        }

        UserModel userModel = optionalUserModel.get();
        String personUserName = userModel.getUsername();
        String personPassword = userModel.getPassword();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userModel.getRole()));

        return new User(personUserName, personPassword, authorities);
    }
}
