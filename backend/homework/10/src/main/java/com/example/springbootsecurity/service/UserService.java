package com.example.springbootsecurity.service;

import com.example.springbootsecurity.dao.UserDAO;
import com.example.springbootsecurity.Entity.User;
import com.example.springbootsecurity.exception.custom.MyCustomException;
import com.example.springbootsecurity.exception.custom.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.springbootsecurity.dao.UserDAO.userList;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void addPerson(User user){
        userDAO.addPerson(user);
    }

    public User getPersonById(int id){
        if(id >= userList.size()){
            throw new MyCustomException("Index is out of bounds");
        }
        return userDAO.getPersonByIdx(id);
    }

    public User getPersonUsername(String name){
        for(User p : userDAO.getAllPersons()){
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        throw new UserNotFoundException("User " + name + " not found");
    }
}
