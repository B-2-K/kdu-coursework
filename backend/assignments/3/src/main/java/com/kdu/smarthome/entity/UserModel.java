package com.kdu.smarthome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role = "ROLE_USER";
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany
    private List<House> houses = new ArrayList<>();
}
