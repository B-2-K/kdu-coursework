package com.example.demo.entity;

import javax.validation.constraints.NotNull;

/**
 * Represents a vehicle entity with basic information such as ID, name, and price.
 */
public class Vehicle {
    /**
     * The unique identifier for the vehicle.
     */
    private Long id;

    /**
     * The name of the vehicle.
     */
    @NotNull
    private String name;

    /**
     * The price of the vehicle.
     * It's recommended to use a more appropriate data type for monetary values, such as BigDecimal.
     */
    @NotNull
    private int price;

    /**
     * Constructs a new Vehicle with the specified ID, name, and price.
     *
     * @param id    The unique identifier for the vehicle.
     * @param name  The name of the vehicle.
     * @param price The price of the vehicle.
     */
    public Vehicle(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the ID of the vehicle.
     *
     * @return The unique identifier of the vehicle.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the vehicle.
     *
     * @param id The unique identifier for the vehicle.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the vehicle.
     *
     * @return The name of the vehicle.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vehicle.
     *
     * @param name The name of the vehicle.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the vehicle.
     *
     * @return The price of the vehicle.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the vehicle.
     *
     * @param price The price of the vehicle.
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
