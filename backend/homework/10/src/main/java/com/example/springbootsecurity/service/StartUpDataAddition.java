package com.example.springbootsecurity.service;

import com.example.springbootsecurity.dao.UserDAO;
import com.example.springbootsecurity.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {
    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userDAO.addPerson(new User("Rohit", "rohit", passwordEncoder.encode("Testing123"), "ROLE_ADMIN"));
        userDAO.addPerson(new User("Ajay", "ajay", passwordEncoder.encode("Testing123"), "ROLE_USER"));
    }
}
