package com.example.springbootsecurity.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String username;
    private String password;
    private String role;
}
