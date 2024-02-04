package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String houseName;
    private double latitude;
    private double longitude;
    @ManyToMany(mappedBy = "houses",cascade = CascadeType.ALL)
    private List<UserModel> usersList = new ArrayList<>();
    @OneToMany(mappedBy = "house")
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();
}