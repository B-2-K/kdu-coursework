package org.beans;

public class Vehicle {
    String name;
    private Speaker speaker;
    private Tyre tyre;
    private double price;

    public Vehicle(String name, Speaker speaker, Tyre tyre) {
        this.name = name;
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = tyre.getPrice() + speaker.getPrice() + generateRandomPrice();
    }

    public String getName() {
        return name;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    private double generateRandomPrice() {
        return Math.random() * 100000;
    }

    public double getPrice() {
        return price;
    }
}

