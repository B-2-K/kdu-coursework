package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dao.UserDAO;
import com.example.springbootapidemo.model.User;
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
