package com.example.springtest.service;

import com.example.springtest.dao.PersonDAO;
import com.example.springtest.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person("Rohit", "rohit", passwordEncoder.encode("Testing123"), "ROLE_ADMIN"));
        personDAO.addPerson(new Person("Ajay", "ajay", passwordEncoder.encode("Testing123"), "ROLE_USER"));
    }
}
