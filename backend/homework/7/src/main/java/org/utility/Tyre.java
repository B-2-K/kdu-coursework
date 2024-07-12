package org.utility;

/**
 * Represents a Tyre with a brand and price.
 */
public class Tyre {

    private String brand;
    private double price;

    /**
     * Constructs a Tyre object with the specified brand and price.
     *
     * @param brand The brand of the tyre.
     * @param price The price of the tyre.
     */
    public Tyre(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    /**
     * Gets the brand of the tyre.
     *
     * @return The brand of the tyre.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the price of the tyre.
     *
     * @return The price of the tyre.
     */
    public double getPrice() {
        return price;
    }
}
