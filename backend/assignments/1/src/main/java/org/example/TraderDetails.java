package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
public class TraderDetails {
    public static final HashMap<String, Trader> traders = Main.traders;
    private static TraderDetails accessTraders = new TraderDetails();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TraderDetails.class);
    private TraderDetails(){
    }
    public static TraderDetails getAccessTraders() {
        if(accessTraders == null){
            accessTraders = new TraderDetails();
        }
        return accessTraders;
    }
    public static synchronized Trader getTrader(String address) {
        Trader trader = traders.get(address);
        if (trader == null) {
            logger.info("trader doesn't exist with address : {}", address);
        }
        return trader;
    }
    public static void printTopNTraders(int n){
        List<Trader> traderCollection = traders.values().stream().sorted((trader, t1) -> Double.compare(t1.getProfitLoss(),trader.getProfitLoss())).limit(n).toList();
        traderCollection.forEach(trader -> logger.info(trader.toString()));
    }
    public static void printLastNTraders(int n){
        List<Trader> traderCollection = traders.values().stream().sorted((trader, t1) -> Double.compare(trader.getProfitLoss(),t1.getProfitLoss())).limit(n).toList();
        traderCollection.forEach(trader -> logger.info(trader.toString()));
    }
}