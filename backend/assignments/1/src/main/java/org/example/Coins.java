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
    public Coins(int i, String bitcoin, String btc, double price, long volume) {
        this.rank = i;
        this.name = bitcoin;
        this.symbol = btc;
        this.price = price;
        this.circulationSupply = volume;
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
    public  void setSymbol(String symbol) {
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
        synchronized (this){
            this.circulationSupply = circulationSupply;
            notifyAll();
        }
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
