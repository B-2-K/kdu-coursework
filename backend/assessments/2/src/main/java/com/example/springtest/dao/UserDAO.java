package com.example.springtest.dao;

import com.example.springtest.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    List<User> userList;

    public UserDAO() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        userList.add(user);
    }

    public User getUserByIdx(int index){
        return userList.get(index);
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public User searchUser(String name){
        User temp = null;
        for (User user : userList) {
            if (user.getName().equals(name)) {
                temp = user;
            }
        }
        return temp;
    }
}
