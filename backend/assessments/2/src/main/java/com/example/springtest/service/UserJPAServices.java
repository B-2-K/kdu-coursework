package com.example.springtest.service;

import com.example.springtest.entities.UserJPA;
import com.example.springtest.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJPAServices {
    @Autowired
    private UserJPARepository userJPARepository;

    public List<UserJPA> getAllUsers(){
        return userJPARepository.findAll();
    }

    public void saveUserJPA(UserJPA user){
        userJPARepository.save(user);
    }
}
