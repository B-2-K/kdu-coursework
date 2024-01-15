package org.example;

import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class Trader{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Trader.class);
    private int id;
    private String firstName;
    private String lastName;
    private String walletAddress;
    private String phone;
    private double profitLoss;
    private Map<String, Long> coinToVolume = new HashMap<>();
    public Map<String, Long> getCoinToVolume() {
        return coinToVolume;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getFirstName() {
        return firstName;
    }

    public  void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public  void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public  String getWalletAddress() {
        return walletAddress;
    }

    public  void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public  String getPhone() {
        return phone;
    }

    public  void setPhone(String phone) {
        this.phone = phone;
    }

    public  void printPortfolio() {
        logger.info("Net profit-loss: {}", profitLoss);
        coinToVolume.forEach((symbol, volume) -> logger.info("Symbol: {}, Volume: {}", symbol, volume));
    }

    public  void buyCoin(String symbol,Long volume,double price) {
        // update portfolio
        Long totalQuantity = coinToVolume.getOrDefault(symbol,0L);
        coinToVolume.put(symbol,totalQuantity + volume);
        profitLoss -= volume*price;
    }

    public  void sellCoin(String symbol, Long volume, double price) {
        Long totalQuantity = coinToVolume.getOrDefault(symbol,0L);
        if(volume > totalQuantity){
            logger.info("Not enough volume to sell");
        }
        coinToVolume.put(symbol,totalQuantity - volume);
        profitLoss += volume*price;
    }
}