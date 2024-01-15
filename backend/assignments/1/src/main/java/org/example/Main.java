package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    public static Map<String, Double> traderBalance = ExecuteTransaction.traderBalance;
    public static Map<String, Long> traderPortfolio = ExecuteTransaction.traderPortfolio;
    public static Map<String, Trader> traderHashMap = CsvReader.traderHashMap;
    public static Map<String, Coins> coinHashMap = CsvReader.coinHashMap;
//    public static JsonNode transactions;

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            // Load coins and traders from CSV files
            CsvReader.loadCoins("src/main/resources/coins.csv");
            CsvReader.loadTraders("src/main/resources/traders.csv");

            // Load transactions from JSON file
            JsonNode transaction = parseJsonFile("src/main/resources/test_transaction.json");
            executeTransactions(transaction, new CountDownLatch(transaction.size()));

            // Perform additional data querying or display results as needed
            displayCoinDetails();
            displayTopCoins();
            displayTraderPortfolio();
            displayTopTraders();
            displayBottomTraders();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor to release resources
            if (executorService != null) {
                executorService.shutdown();

                try {
                    // Wait for all threads to complete or until timeout (e.g., 10 seconds)
                    if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                        // If timeout occurs, force shutdown
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    // Handle InterruptedException
                    e.printStackTrace();
                }
            }
        }
    }

    public static JsonNode parseJsonFile(String file) {
        JsonNode jsonNode = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            File from = new File(file);
            jsonNode = mapper.readTree(from);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return jsonNode;
    }

    public static void executeTransactions(JsonNode jsonNode, CountDownLatch latch) {
        for (JsonNode transaction : jsonNode) {
            ExecuteTransaction executeTransaction = new ExecuteTransaction(transaction, latch);
            executeTransaction.run();
            getBlockHash();
        }
    }

    public static void displayCoinDetails(){
        logger.info("Coin Details :");
        coinHashMap.forEach((key, value) -> {
            logger.info("Coin Name : {}, Coin Symbol : {}, Coin Price : {}, Coin Volume : {}", value.getCoinName(), value.getCoinSymbol(), value.getPrice(), value.getVolume());
        });
    }

    public static void displayTopCoins() {
        List<Coins> topCoins = coinHashMap.values().stream()
                .sorted(Comparator.comparingDouble(Coins::getPrice).reversed())
                .limit(5)
                .collect(Collectors.toList());

        logger.info("Top 5 Coins :");
        for(Coins coin : topCoins) {
            logger.info("Coin Name : {}, Coin Symbol : {}, Coin Price : {}, Coin Volume : {}", coin.getCoinName(), coin.getCoinSymbol(), coin.getPrice(), coin.getVolume());
        }
    }

    public static void displayTraderPortfolio() {
        traderHashMap.forEach((traderAddress, trader) -> {
            logger.info("Trader Details : ");
            Trader currTrader = traderHashMap.get(traderAddress);
            logger.info("First Name: {}, Last Name : {}, Phone Number : {}, Wallet Address : {}", currTrader.getFirstName(), currTrader.getLastName(), currTrader.getPhoneNumber(), currTrader.getWalletAddress());
            List<Coins> result = new ArrayList<>();
            traderPortfolio.forEach((key, value) -> {
                // first 34 characters of the key is the wallet address and rest is the coin code
                String address = key.substring(0, 34);
                String coinCode = key.substring(34);

                if (address.equals(traderAddress)) {
                    Coins curr = coinHashMap.get(coinCode);
                    result.add(curr);
                }
            });

            logger.info("Trader Coins : ");

            for (Coins curr : result) {
                logger.info("Coin Name : {}, Coin Symbol : {}, Coin Price : {}, Coin Volume : {}", curr.getCoinName(), curr.getCoinSymbol(), curr.getPrice(), curr.getVolume());
            }
        });
    }

    public static void displayTopTraders() {
        List<String> topTraders = traderBalance.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        logger.info("Top 5 Traders : ");

        for(int i = 0;i < topTraders.size();i++) {
            Trader currTrader = traderHashMap.get(topTraders.get(i));
            logger.info("First Name: {}, Last Name : {}, Phone Number : {}, Wallet Address : {}, Balance : {}", currTrader.getFirstName(), currTrader.getLastName(), currTrader.getPhoneNumber(), currTrader.getWalletAddress(), traderBalance.get(topTraders.get(i)));
        }
    }

    public static void displayBottomTraders() {
        List<String> bottomTraders = traderBalance.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        logger.info("Bottom 5 Traders : ");
        for(int i = 0;i < bottomTraders.size();i++) {
            Trader currTrader = traderHashMap.get(bottomTraders.get(i));
            logger.info("First Name: {}, Last Name : {}, Phone Number : {}, Wallet Address : {}, Balance : {}", currTrader.getFirstName(), currTrader.getLastName(), currTrader.getPhoneNumber(), currTrader.getWalletAddress(), traderBalance.get(bottomTraders.get(i)));
        }
    }

    private static String getBlockHash() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();

        for (double i = 0; i < 199999999; i++) {
            i = i;
        }

        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }

        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}

