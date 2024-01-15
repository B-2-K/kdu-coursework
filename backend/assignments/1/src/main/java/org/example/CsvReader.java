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

    public static ArrayList<String[]> loadCoins(String filePath) throws IOException {
        ArrayList<String[]> result = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Skip the header row if present
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0].trim());
                int rank = Integer.parseInt(line[1].trim());
                String name = line[2].trim();
                String symbol = line[3].trim();
                double price = Double.parseDouble(line[4].trim());
                long volume = Long.parseLong(line[5].trim());

                Coins coin = new Coins(rank, name, symbol, price, volume);
                coinHashMap.put(symbol, coin);

                // Construct a string array representing the coin and add it to the result list
                String[] coinArray = {String.valueOf(id), String.valueOf(rank), name, symbol, String.valueOf(price), String.valueOf(volume)};
                result.add(coinArray);
            }
        } catch (Exception e) {
            logger.error("Error while reading the coins data : ", e);
        }

        // Return the ArrayList of string arrays
        return result;
    }



    public static ArrayList<String[]> loadTraders(String filePath) throws IOException {
        ArrayList<String[]> result = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Skip the header row if present
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0].trim());
                String firstName = line[1].trim();
                String lastName = line[2].trim();
                String phoneNumber = line[3].trim();
                String walletAddress = line[4].trim();

                Trader trader = new Trader(firstName, lastName, phoneNumber, walletAddress);
                traderHashMap.put(walletAddress, trader);

                // Construct a string array representing the trader and add it to the result list
                String[] traderArray = {String.valueOf(id), firstName, lastName, phoneNumber, walletAddress};
                result.add(traderArray);
            }
        } catch (Exception e) {
            logger.error("Error while reading the traders data : ", e);
        }

        // Return the ArrayList of string arrays
        return result;
    }

}

