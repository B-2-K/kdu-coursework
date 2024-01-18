package org.example;

import org.slf4j.LoggerFactory;

public class Coins {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Coins.class);
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long circulationSupply;
    public Coins(){
    }
    public Coins(int rank, String name, String symbol, double price, long circulationSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulationSupply = circulationSupply;
    }
    public  void setRank(int rank) {
        this.rank = rank;
    }
    public  String getCoinName() {
        return name;
    }
    public  void setCoinName(String name) {
        this.name = name;
    }
    public String getCoinSymbol(){
        return symbol;
    }
    public  void setCoinSymbol(String symbol) {
        this.symbol = symbol;
    }
    // sync updates in coin....
    public synchronized double getPrice() {
        return price;
    }
    public synchronized void setPrice(double price) {
        this.price = price;
    }
    public synchronized long getCirculationSupply() {
        return circulationSupply;
    }

    public void setCirculationSupply(long circulationSupply) {
        this.circulationSupply = circulationSupply;
    }

    // display sinc ?
    public void printCoinDetails() {
        logger.info("Coin Details : ");
        logger.info("Rank : {}", rank);
        logger.info("Symbol : {}", symbol);
        logger.info("Price : {}", price);
        logger.info("Circulation Supply : {}", circulationSupply);
    }
}
