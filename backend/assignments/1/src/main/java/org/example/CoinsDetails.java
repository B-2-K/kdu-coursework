package org.example;

import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
public class CoinsDetails {
    public static final HashMap<String, Coins> coins = Main.coins;
    private static CoinsDetails accessCoins = new CoinsDetails();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CoinsDetails.class);
    private CoinsDetails(){
    }
    public static CoinsDetails getAccessCoins() {
        if(accessCoins == null){
            accessCoins = new CoinsDetails();
        }
        return accessCoins;
    }

    public static synchronized Coins getCoins(String address) {
        Coins coin = coins.get(address);
        if (coin == null) {
            logger.info("coins doesn't exist with address : {}", address);
        }
        return coin;
    }

    public static void printTopNcoins(int n){
        List<Coins> coinsCollection = coins.values().stream().sorted((coin, t1) -> Double.compare(t1.getPrice(),coin.getPrice())).limit(n).toList();
        coinsCollection.forEach(Coins::printCoinDetails);
    }
}
