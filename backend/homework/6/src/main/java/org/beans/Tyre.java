package org.beans;

public class Tyre {
    private String brand;
    private double price;

    public Tyre(String brand, double price) {
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
