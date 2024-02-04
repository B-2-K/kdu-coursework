package com.kdu.smarthome.security.config;

import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Custom authentication manager for handling user authentication.
 */
@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    private final UserRepository userRepository;

    /**
     * Constructor for CustomAuthenticationManager.
     *
     * @param userRepository The repository for accessing user data.
     */
    @Autowired
    public CustomAuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("No user registered with this username!");
        } else {
            UserModel userModel = optionalUser.get();
            if (passwordEncoder().matches(password, userModel.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(userModel.getRole()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }

    /**
     * Bean for creating a password encoder (BCryptPasswordEncoder).
     *
     * @return The password encoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
