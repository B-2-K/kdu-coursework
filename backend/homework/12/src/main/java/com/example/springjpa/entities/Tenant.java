package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tenant")
    private List<Shift> shifts;

    @OneToMany(mappedBy = "tenant")
    private List<ShiftType> shiftTypes;

    @OneToMany(mappedBy = "tenant")
    private List<User> users;
}
