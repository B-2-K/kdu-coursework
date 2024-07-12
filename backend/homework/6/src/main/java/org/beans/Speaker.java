package org.beans;

public class Speaker {
    private String brand;
    private double price;

    public Speaker(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
}
