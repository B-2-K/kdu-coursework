package com.example.springtest.service;

import com.example.springtest.entities.Product;
import com.example.springtest.entities.UserJPA;
import com.example.springtest.repository.ProductRepository;
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

    public UserJPA getUserById(Long id){
        UserJPA user = null;
        for(UserJPA p : userJPARepository.findAll()){
            if(p.getId() == id){
                user = p;
                break;
            }
        }
        return user;
    }

    public void buyProductById(int id, int Quantity){
        // update the inventory and cart accordingly.
    }
}
