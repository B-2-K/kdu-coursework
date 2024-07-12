package com.example.springtest.service;

import com.example.springtest.dao.UserDAO;
import com.example.springtest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return userDAO.getUserByIdx(id);
    }

    public User getUserByUsername(String username) {
        return userDAO.searchUser(username);
    }
}
