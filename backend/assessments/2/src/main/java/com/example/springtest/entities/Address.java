package com.example.springtest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String code;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            referencedColumnName = "id"
    )
    private UserJPA userJPA;
}
