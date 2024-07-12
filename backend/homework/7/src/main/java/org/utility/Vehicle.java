package org.utility;

/**
 * Represents a Vehicle with a name, speaker, tyre, and price.
 */
public class Vehicle {
    private String name;
    private Speaker speaker;
    private Tyre tyre;
    private double price;

    /**
     * Constructs a Vehicle object with the specified name, speaker, and tyre.
     *
     * @param name    The name of the vehicle.
     * @param speaker The speaker of the vehicle.
     * @param tyre    The tyre of the vehicle.
     */
    public Vehicle(String name, Speaker speaker, Tyre tyre) {
        this.name = name;
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = tyre.getPrice() + speaker.getPrice() + constantPrice();
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
     * Gets the speaker of the vehicle.
     *
     * @return The speaker of the vehicle.
     */
    public Speaker getSpeaker() {
        return speaker;
    }

    /**
     * Gets the tyre of the vehicle.
     *
     * @return The tyre of the vehicle.
     */
    public Tyre getTyre() {
        return tyre;
    }

    /**
     * Gets the constant price for the vehicle.
     *
     * @return The constant price for the vehicle.
     */
    private double constantPrice() {
        return 10000.0;
    }

    /**
     * Gets the total price of the vehicle.
     *
     * @return The total price of the vehicle.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the vehicle.
     *
     * @param price The new price of the vehicle.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
