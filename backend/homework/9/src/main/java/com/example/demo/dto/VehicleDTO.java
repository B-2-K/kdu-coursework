package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) representing a Vehicle.
 * This class encapsulates information about a vehicle, such as its ID, name, and price.
 */
@Data
@AllArgsConstructor
public class VehicleDTO {
    private Long id;
    private String name;
    private int price;
}
