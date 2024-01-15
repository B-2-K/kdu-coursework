package org.example;

public class Coins {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long volume;

    // Constructors
    public Coins(int rank, String name, String symbol, double price, long volume) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
    }

    // Get Name
    public String getCoinName() {
        return this.name;
    }

    // Get Code
    public String getCoinSymbol() {
        return this.symbol;
    }

    // Get Price
    public double getPrice() {
        return this.price;
    }

    // Set Price
    public void setPrice(double price) {
        this.price = price;
    }

    // Set Volume
    public void setVolume(long volume) {
        this.volume = volume;
    }

    // Get Volume
    public long getVolume() {
        return this.volume;
    }
}
