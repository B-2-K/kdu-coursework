package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    public static final HashMap<String, Trader> walletAddressToTraders = new HashMap<>();
    public static final HashMap<String, Coins> symbolToCoins = new HashMap<>();
    static JsonNode jsonNode;
    static CountDownLatch countDownLatch;
    static ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {
        // initial coins and traders loaded
        loadTraders("/home/hp/Desktop/KDU/Assignment1/src/main/resources/traders.csv");
        loadCoins("/home/hp/Desktop/KDU/Assignment1/src/main/resources/coins.csv");
        String jsonFilePath = "/home/hp/Desktop/KDU/Assignment1/src/main/resources/test_transaction.json";
        parseJsonFile(jsonFilePath);
        countDownLatch = new CountDownLatch(jsonNode.size() + 1);
        Thread thread = new Thread(() -> executeTransactions(jsonNode,countDownLatch));
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.warn(e.getMessage());
        }finally {
            // Shutdown the executor service
            executorService.shutdown();
        }
        displayCoins();
        displayTraders();
        displayTopCoins();
        displayTopTraders();
        displayBottomTraders();
    }

    public static List<String[]> parseCSV(Path coinCsvPath){
        ArrayList<String[]> result = new ArrayList<>();
        String line = "";
        String filePath = coinCsvPath.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            line = reader.readLine(); // Read and discard the header line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                result.add(data);
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
        return result;
    }

    public static  void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        executorService = Executors.newFixedThreadPool((int)latch.getCount());
        for (JsonNode jsonNode: jsonTransactions){
            executorService.submit(new ExecuteTransaction(jsonNode,latch));
        }
        executorService.shutdown();
    }

    public static void loadTraders(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] traderDetails = r.get(i);
                Trader trader = new Trader();

                int id = Integer.parseInt(traderDetails[0]);
                String firstName = traderDetails[1];
                String lastName = traderDetails[2];
                String phone = traderDetails[3];
                String walletAddress = traderDetails[4];

                trader.setId(id);
                trader.setFirstName(firstName);
                trader.setLastName(lastName);
                trader.setPhone(phone);
                trader.setWalletAddress(walletAddress);

                walletAddressToTraders.put(walletAddress,trader);
            }
            logger.info("Traders Loaded.....");
        } catch (IOException | CsvException e) {
            logger.error(e.getMessage());
        }
    }

    public static void loadCoins(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] coinsDetails = r.get(i);
                Coins coin = new Coins();

                int rank = Integer.parseInt(coinsDetails[1]);
                String name = coinsDetails[2];
                String symbol = coinsDetails[3];
                double price = Double.parseDouble(coinsDetails[4]);
                long circulationSupply = Long.parseLong(coinsDetails[5]);

                coin.setRank(rank);
                coin.setCoinName(name);
                coin.setCoinSymbol(symbol);
                coin.setPrice(price);
                coin.setCirculationSupply(circulationSupply);

                symbolToCoins.put(coin.getCoinSymbol(), coin);
            }
            logger.info("coins Loaded.....");
        } catch (IOException | CsvException e) {
            logger.error(e.getMessage());
        }
    }

    public static void displayCoins(){
        symbolToCoins.forEach((key, value) ->{
            Coins coin = symbolToCoins.get(key);
            logger.info("Name : {}, Symbol : {}, Price : {}, Volume : {}", coin.getCoinName(), coin.getCoinSymbol(), coin.getPrice(), coin.getCirculationSupply());
        });
    }

    public static void displayTraders(){
        walletAddressToTraders.forEach((key, value)->{
            Trader trader = walletAddressToTraders.get(key);
            logger.info("First Name : {}, Last Name : {}, Wallet Address : {}, Phone : {}", trader.getFirstName(), trader.getLastName(), trader.getWalletAddress(), trader.getPhone());
            trader.printPortfolio();
        });
    }

    public static void displayTopCoins(){
        logger.info("Top 5 coins : ");
        CoinsDetails.printTopNcoins(5);
    }

    public static void displayTopTraders(){
        logger.info("Top five traders : ");
        TraderDetails.printTopNTraders(5);
    }

    public static void displayBottomTraders(){
        logger.info("Bottom five traders : ");
        TraderDetails.printLastNTraders(5);
    }

    public static JsonNode parseJsonFile(String jsonString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(new File(jsonString));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonNode;
    }
}
