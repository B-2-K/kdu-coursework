package org.assignment1;

import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ExecuteTransaction implements Runnable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);
    public static HashMap<String, Double> traderBalance = new HashMap<>();
    public static HashMap<String, Long> traderPortfolio = new HashMap<>();
    public static HashMap<String, Coin> coinHashMap = CsvReader.coinHashMap;
    private final Transaction transaction;
    private final CountDownLatch latch;
    private final String coinName;
    private final String walletAddress;
    private final long quantity;
    private final long volume;
    private final double price;

    public ExecuteTransaction(Transaction transaction, CountDownLatch latch, String coinName, String walletAddress, long quantity, long volume, double price) {
        this.transaction = transaction;
        this.latch = latch;
        this.coinName = coinName;
        this.walletAddress = walletAddress;
        this.quantity = quantity;
        this.volume = volume;
        this.price = price;
    }

    @Override
    public void run() {
        try {
            switch (transaction.getType()) {
                case BUY:
                    executeBuyTransaction();
                    break;
                case SELL:
                    executeSellTransaction();
                    break;
                case UPDATE_PRICE:
                    handleUpdatePriceTransaction();
                    break;
                case ADD_VOLUME:
                    handleAddVolumeTransaction();
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

    private synchronized void executeBuyTransaction() {
        Coin coin = coinHashMap.get(coinName);
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
        String key = walletAddress + coin.getCode();
        boolean isFound = traderPortfolio.containsKey(key);
        if (isFound) {
            traderPortfolio.put(key, traderPortfolio.get(key) + quantity);
            traderBalance.put(walletAddress, traderBalance.get(walletAddress) + totalCost);
        } else {
            traderPortfolio.put(key, quantity);
            traderBalance.put(walletAddress, totalCost);
        }

        logger.info("Buy transaction executed successfully {}", coin.getCode() + " " + quantity);
    }


    private synchronized void executeSellTransaction() {
        Coin coin = coinHashMap.get(coinName);
        String key = walletAddress + coin.getCode();
        long coinVolume = coin.getVolume();
        boolean isFound = traderPortfolio.containsKey(key);
        if(isFound == false) {
            logger.info("Insufficient funds");
            return;
        }
        long currQuantity = traderPortfolio.get(key);
        if(currQuantity < quantity){
            logger.info("Insufficient funds");
            return;
        }
        long currVolume = coinVolume + quantity;
        coin.setVolume(currVolume);
        double totalCost = quantity * coin.getPrice();
        traderBalance.put(walletAddress, traderBalance.get(walletAddress) - totalCost);
        currQuantity -= quantity;
        traderPortfolio.put(key, currQuantity);
        logger.info("Sell transaction executed successfully.");
    }

    private synchronized void handleUpdatePriceTransaction() {
        Coin coin = coinHashMap.get(coinName);
        coin.setPrice(price);
    }

    private synchronized void handleAddVolumeTransaction() {
        // Find the corresponding coin
        Coin coin = coinHashMap.get(coinName);
        coin.setVolume(coin.getVolume() + volume);
    }
}
