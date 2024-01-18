package org.example;

import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
public class CoinsDetails {
    public static final HashMap<String, Coins> symbolToCoins = Main.symbolToCoins;
    private static CoinsDetails coinInfo = new CoinsDetails();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CoinsDetails.class);
    private CoinsDetails(){

    }

    public static CoinsDetails getCoinInfo() {
        if(coinInfo == null){
            coinInfo = new CoinsDetails();
        }
        return coinInfo;
    }

    public static synchronized Coins getCoins(String address) {
        Coins coin = symbolToCoins.get(address);
        if (coin == null) {
            logger.info("coins doesn't exist with address : {}", address);
        }
        return coin;
    }

    public static void printTopNcoins(int n){
        List<Coins> coinsCollection = symbolToCoins.values().stream().sorted((coin, t1) -> Double.compare(t1.getPrice(),coin.getPrice())).limit(n).toList();
        coinsCollection.forEach(Coins::printCoinDetails);
    }
}
