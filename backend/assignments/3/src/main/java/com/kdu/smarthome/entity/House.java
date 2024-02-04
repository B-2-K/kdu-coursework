package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "house")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String houseName;

    @ManyToMany(mappedBy = "houses",cascade = CascadeType.ALL)
    private List<UserModel> usersList = new ArrayList<>();

    @OneToMany(mappedBy = "house")
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.of(2024,12,31,23,59);

}