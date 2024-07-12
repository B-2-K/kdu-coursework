package org.utility;

/**
 * Represents a Speaker with a brand and price.
 */
public class Speaker {

    private String brand;
    private double price;

    /**
     * Constructs a Speaker object with the specified brand and price.
     *
     * @param brand The brand of the speaker.
     * @param price The price of the speaker.
     */
    public Speaker(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    /**
     * Gets the brand of the speaker.
     *
     * @return The brand of the speaker.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the price of the speaker.
     *
     * @return The price of the speaker.
     */
    public double getPrice() {
        return price;
    }
}
