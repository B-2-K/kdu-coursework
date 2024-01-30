package com.example.springbootsecurity.dao;

import com.example.springbootsecurity.Entity.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    public static final List<User> userList = new ArrayList<User>();

    public void addPerson(User user){
        userList.add(user);
    }

    public User getPersonByIdx(int index){
        return userList.get(index);
    }

    public String getRoleByPersonIdx(int index){
        return userList.get(index).getRole();
    }

    public List<User> getAllPersons(){
        return userList;
    }
}
