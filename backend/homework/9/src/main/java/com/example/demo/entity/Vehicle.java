package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull

/**
 * Represents a vehicle entity with basic information such as ID, name, and price.
 */
public class Vehicle {
    private Long id;
    private String name;
    private int price;
}
