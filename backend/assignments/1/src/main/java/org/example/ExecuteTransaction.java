package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);
    private JsonNode jsonTransaction;
    private CountDownLatch latch;
    public static final TraderDetails traders = TraderDetails.getTraderInfo();
    public static final CoinsDetails coins = CoinsDetails.getCoinInfo();
    public ExecuteTransaction() {

    }
    public ExecuteTransaction(JsonNode transactions, CountDownLatch latch) {
        this.jsonTransaction = transactions;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            getBlockHash();
            JsonNode data = jsonTransaction.get("data");

            if(data == null){
                logger.info("data not found in transaction");
                return;
            }

            String type =  jsonTransaction.get("type").asText();
            switch (type) {
                case "BUY":
                    processBuyTransaction(data);
                    break;
                case "SELL":
                    processSellTransaction(data);
                    break;
                case "ADD_VOLUME":
                    addVolume(data);
                    break;
                case "UPDATE_PRICE":
                    updatePrice(data);
                    break;
                default:
                    logger.error("Enter Valid Input : ");
                    break;
            }
        } finally {
            latch.countDown();
        }

    }

    private synchronized void processBuyTransaction(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);

        if (trader == null){
            logger.info("not a valid org.example.Trader");
            return;
        }

        long supply = coin.getCirculationSupply();
        double price  = coin.getPrice();
        while (quantity > supply){
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            supply  = coin.getCirculationSupply();
        }

        // have enough to buy
        coin.setCirculationSupply(supply - quantity);
        trader.buyCoin(symbol,quantity,price);
        TraderDetails.walletAddressToTraders.put(walletAddress,trader);
        CoinsDetails.symbolToCoins.put(symbol,coin);
        // notifying coin buy was success
        notifyAll();
    }

    private synchronized void processSellTransaction(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            logger.info("Coin not found");
            return;
        }
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Trader trader = traders.getTrader(walletAddress);
        if (trader == null){
            logger.info("not a valid Trader");
            return;
        }
        double price  = coin.getPrice();
        long supply = trader.getCoinToVolume().getOrDefault(symbol,0L);

        if (quantity > supply){
            logger.info("Not enough quantity");
            return;
        }
        // have enough to sell
        trader.sellCoin(symbol,quantity,price);
        coin.setCirculationSupply(coin.getCirculationSupply() + quantity);
        TraderDetails.walletAddressToTraders.put(walletAddress,trader);
        CoinsDetails.symbolToCoins.put(symbol,coin);
        // notifying trader's sell has been done
        notifyAll();

    }
    private synchronized void addVolume(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            logger.info("Coin not found");
            return;
        }
        long volume = data.get("volume").asLong();
        coin.setCirculationSupply(coin.getCirculationSupply() + volume);
        CoinsDetails.symbolToCoins.put(symbol,coin);
        notifyAll();
    }

    private synchronized void updatePrice(JsonNode data) {
        String symbol = data.get("coin").asText();
        Coins coin = coins.getCoins(symbol);
        if(coin == null)
        {
            logger.info("Coin not found");
            return;
        }
        double price = data.get("price").asDouble();
        coin.setPrice(price);
        CoinsDetails.symbolToCoins.put(symbol,coin);
        notifyAll();
    }

    private String getBlockHash() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        // Introducing delay mimicking complex calculation being performed.
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            transactionHash.append(saltChars.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
