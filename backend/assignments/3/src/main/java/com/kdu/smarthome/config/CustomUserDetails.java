package com.kdu.smarthome.config;

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
 * Loads the user
 */
@Component
public class CustomUserDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUserModel = userRepository.findByUsername(username);
        UserModel userModel = null;
        if(optionalUserModel.isPresent())   userModel = optionalUserModel.get();
        String personUserName;
        String personPassword;
        List<GrantedAuthority> authorities;

        if (userModel == null) {
            throw new UsernameNotFoundException("User details not found for user: ".concat(username).concat(". Please register first."));
        } else {
            personUserName = userModel.getUsername();
            personPassword = userModel.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userModel.getRole()));
        }
        return new User(personUserName, personPassword, authorities);
    }
}
