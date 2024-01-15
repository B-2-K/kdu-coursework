package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.JsonNode;

public class ExecuteTransaction implements Runnable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);
    public static final Map<String, Double> traderBalance = new HashMap<>();
    public static final Map<String, Long> traderPortfolio = new HashMap<>();
    public static final Map<String, Coins> coinHashMap = CsvReader.coinHashMap;
    private static String coinName;
    private static String walletAddress;
    private static long quantity;
    private static long volume;
    private static double price;

    JsonNode transactionNode;
    CountDownLatch latch;
    public ExecuteTransaction() {
        logger.error("Testing");
    }
    public ExecuteTransaction(JsonNode transactionNode, CountDownLatch latch) {
        this.transactionNode = transactionNode;
        this.latch = latch;
    }
    @Override
    public void run() {
        JsonNode dataNode = transactionNode.get("data");
        TransactionData data = new ObjectMapper().convertValue(dataNode, TransactionData.class);
        if(data == null) {
            logger.error("Invalid Transaction Data");
            return;
        }
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.valueOf(transactionNode.get("type").asText()));
        transaction.setData(data);

        try {
            switch (transaction.getType()) {
                case BUY:
                    executeBuyTransaction(data);
                    break;
                case SELL:
                    executeSellTransaction(data);
                    break;
                case UPDATE_PRICE:
                    handleUpdatePriceTransaction(data);
                    break;
                case ADD_VOLUME:
                    handleAddVolumeTransaction(data);
                    break;
                default:
                    logger.error("Unknown Transaction");
                    break;
            }
            // Ensure to call latch.countDown() after the transaction is processed
            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void executeBuyTransaction(TransactionData data) {
        coinName = data.getCoin();
        quantity = data.getQuantity();
        Coins coin = coinHashMap.get(coinName);
        if(coin == null){
            logger.error("Invalid coin name");
            return;
        }
        long coinVolume = coin.getVolume();

        while (coinVolume < quantity) {
            logger.info("Insufficient coins available for the buy transaction. Waiting for more coins...");

            try {
                // Sleep for a certain period before checking again
                TimeUnit.SECONDS.sleep(1); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted while waiting for more coins.", e);
            }

            coinVolume = coin.getVolume();
        }


        double totalCost = quantity * coin.getPrice();

        long currVolume = coinVolume - quantity;
        coin.setVolume(currVolume);

        // Add coin to the trader portfolio
        String key = walletAddress + coin.getCoinSymbol();
        boolean isFound = traderPortfolio.containsKey(key);
        if (isFound) {
            double currBalance = traderBalance.get(walletAddress) - totalCost;
            traderPortfolio.put(key, traderPortfolio.get(key) + quantity);
            traderBalance.put(walletAddress, -currBalance);
        } else {
            traderPortfolio.put(key, quantity);
            traderBalance.put(walletAddress, -totalCost);
        }

        logger.info("Buy transaction executed successfully {}", coin.getCoinSymbol() + " " + quantity);
    }


    private synchronized void executeSellTransaction(TransactionData data) {
        coinName = data.getCoin();
        Coins coin = coinHashMap.get(coinName);
        quantity = data.getQuantity();
        walletAddress = data.getWalletAddress();
        String key = walletAddress + coin.getCoinSymbol();
        long coinVolume = coin.getVolume();
        boolean isFound = traderPortfolio.containsKey(key);
        if(isFound == false) {
            logger.info("Insufficient quantity of coins in trader's account");
            return;
        }
        long currQuantity = traderPortfolio.get(key);
        if(currQuantity < quantity){
            logger.info("Insufficient quantity of coins in trader's account");
            return;
        }

        long currVolume = coinVolume + quantity;
        coin.setVolume(currVolume);
        double totalCost = quantity * coin.getPrice();
        traderBalance.put(walletAddress, traderBalance.get(walletAddress) + totalCost);
        currQuantity -= quantity;
        traderPortfolio.put(key, currQuantity);
        logger.info("Sell transaction executed successfully.");
    }

    private synchronized void handleUpdatePriceTransaction(TransactionData data) {
        coinName = data.getCoin();
        price = data.getPrice();
        Coins coin = coinHashMap.get(coinName);
        if(coin == null){
            logger.error("Invalid coin name");
            return;
        }
        coin.setPrice(price);
        logger.info("Price Updated successfully.");
    }

    private synchronized void handleAddVolumeTransaction(TransactionData data) {
        // Find the corresponding coin
        coinName = data.getCoin();
        volume = data.getVolume();
        Coins coin = coinHashMap.get(coinName);
        if(coin == null){
            logger.error("Invalid coin name");
            return;
        }
        coin.setVolume(coin.getVolume() + volume);
        logger.info("Volume Added successfully.");
    }
}
