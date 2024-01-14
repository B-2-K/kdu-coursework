package org.assignment1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    public static HashMap<String, Double> traderBalance = ExecuteTransaction.traderBalance;
    public static HashMap<String, Long> traderPortfolio = ExecuteTransaction.traderPortfolio;
    public static HashMap<String, Trader> traderHashMap = CsvReader.traderHashMap;
    public static HashMap<String, Coin> coinHashMap = CsvReader.coinHashMap;

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            // Load coins and traders from CSV files
            CsvReader.loadCoins("src/main/resources/coins.csv");
            CsvReader.loadTraders("src/main/resources/traders.csv");

            // Load transactions from JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode transactions = objectMapper.readTree(new File("src/main/resources/test_transaction.json"));
            
            // Create a thread pool for processing transactions
            int threadPoolSize = transactions.size();
            executorService = Executors.newFixedThreadPool(threadPoolSize);
            CountDownLatch latch = new CountDownLatch(transactions.size());

            // Execute transactions
            executeTransactions(transactions, executorService, latch);

            // Wait for all transactions to complete
            latch.await();

            // Perform additional data querying or display results as needed
            displayCoinDetails();
            displayTopCoins();
            displayTraderPortfolio();
            displayTopTraders();
            displayBottomTraders();
        } catch (IOException | InterruptedException e) {
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

    private static void executeTransactions(JsonNode jsonTransactions, ExecutorService executorService, CountDownLatch latch) {
        
        for (JsonNode transactionNode : jsonTransactions) {
            JsonNode dataNode = transactionNode.get("data");
            TransactionData data = new ObjectMapper().convertValue(dataNode, TransactionData.class);

            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.valueOf(transactionNode.get("type").asText()));
            transaction.setData(data);
            String coin = data.getCoin();
            String walletAddress = data.getWalletAddress();
            long quantity = data.getQuantity();
            long volume = data.getVolume();
            double price = data.getPrice();
            // Pass the CountDownLatch to ExecuteTransaction
            Runnable task = new ExecuteTransaction(transaction, latch, coin, walletAddress, quantity, volume, price);
            executorService.submit(task);
        }
    }

    public static void displayCoinDetails(){
        coinHashMap.forEach((key, value) -> {
            logger.info("Coin Name : {}, Coin Symbol : {}, Coin Price : {}, Coin Volume : {}", value.getName(), value.getCode(), value.getPrice(), value.getVolume());
        });
    }

    public static void displayTopCoins() {
        List<Coin> topCoins = coinHashMap.values().stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(5)
                .collect(Collectors.toList());

        logger.info("Top 5 Coins : {}", topCoins);
    }

    public static void displayTraderPortfolio() {
        traderHashMap.forEach((traderAddress, trader) -> {
            logger.info("Trader Details : ");
            Trader currTrader = traderHashMap.get(traderAddress);
            logger.info("First Name: {}, Last Name : {}, Phone Number : {}, Wallet Address : {}", currTrader.getFirstName(), currTrader.getLastName(), currTrader.getPhoneNumber(), currTrader.getWalletAddress());
            List<Coin> result = new ArrayList<>();
            traderPortfolio.forEach((key, value) -> {
                // first 34 characters of the key is the wallet address and rest is the coin code
                String address = key.substring(0, 34);
                String coinCode = key.substring(34);

                if (address.equals(traderAddress)) {
                    Coin curr = coinHashMap.get(coinCode);
                    result.add(curr);
                }
            });

            logger.info("Trader Coins : ");

            for (Coin curr : result) {
                logger.info("Coin Name : {}, Coin Symbol : {}, Coin Price : {}, Coin Volume : {}", curr.getName(), curr.getCode(), curr.getPrice(), curr.getVolume());
            }
        });
    }

    public static void displayTopTraders() {
        List<String> topTraders = traderBalance.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        logger.info("Top 5 Traders : {}", topTraders);
    }

    public static void displayBottomTraders() {
        List<String> bottomTraders = traderBalance.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        logger.info("Bottom 5 Traders : {}", bottomTraders);
    }
}

