package org.example;

import com.opencsv.CSVReader;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReader {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CSVReader.class);
    public static final Map<String, Coins> coinHashMap = new HashMap<>();
    public static final Map<String, Trader> traderHashMap = new HashMap<>();

    public static void loadCoins(String filePath) throws IOException {

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Skip the header row if present
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                String name = line[2].trim();
                String symbol = line[3].trim();
                double price = Double.parseDouble(line[4].trim());
                long volume = Long.parseLong(line[5].trim());

                Coins coin = new Coins(name, symbol, price, volume);
                coinHashMap.put(symbol, coin);
            }
        } catch (Exception e) {
            logger.error("Error while reading the coins data : ", e);
        }
    }

    public static void loadTraders(String filePath) throws IOException {

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Skip the header row if present
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                String firstName = line[1].trim();
                String lastName = line[2].trim();
                String phoneNumber = line[3].trim();
                String walletAddress = (line[4].trim());

                Trader trader = new Trader(firstName, lastName, phoneNumber, walletAddress);
                traderHashMap.put(walletAddress, trader);
            }
        } catch (Exception e) {
            logger.error("Error while reading the traders data : ", e);
        }
    }
}

