package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Main{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public static Map<String, Player> bowlerToPlayer = new HashMap<String, Player>();
    public static void main(String[] args) {
        loadPlayers("/home/hp/Desktop/KDU/assessment1/src/main/resources/IPL_2021-data.csv");
        bowlersWithMoreThan40Wickets();
        highestWitcketTakerAndhighestRunScorer();
        top3RunScorerAndTop3WitcketTaker();
        addMatchFixture("/home/hp/Desktop/KDU/assessment1/src/main/resources/MatchFixture.csv");
    }

    public static void loadPlayers(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            for (int i = 1; i < r.size(); i++) {
                String[] playerDetails = r.get(i);

                String name = playerDetails[0];
                String team = playerDetails[1];
                String role = playerDetails[2];
                int mathces = Integer.parseInt(playerDetails[3]);
                int runs = Integer.parseInt(playerDetails[4]);
                double average = Double.parseDouble(playerDetails[5]);
                double strikeRate = Double.parseDouble(playerDetails[6]);
                int witckets = Integer.parseInt(playerDetails[7]);

                System.out.println(name + " " + team + " " + role + " " + mathces + " " + runs + " " + average + " " + strikeRate + " " + witckets);

                Player player = new Player(name, team, role, mathces, runs, average, strikeRate, witckets);
                if(role.equals("BOWLER")){
                    bowlerToPlayer.put(name, player);
                }
            }
            logger.info("Players Loaded.....");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void bowlersWithMoreThan40Wickets(){
        String teamName = "CSK";
        bowlerToPlayer.forEach((key, value) ->{
            Player player = bowlerToPlayer.get(key);
            if(player.getWitckets() >= 40 && player.getTeam().equals(teamName)){
                logger.info("Players Name : {}", player.getName());
            }
        });
    }

    public static void highestWitcketTakerAndhighestRunScorer(){
        String teamName = "CSK";
        Map.Entry<String, Player> maxRunEntry = null;
        Map.Entry<String, Player> maxWitcketEntry = null;

        for (Map.Entry<String, Player> currentEntry : bowlerToPlayer.entrySet()) {
            String currTeamName = currentEntry.getValue().getTeam();
            if(currTeamName.equals(teamName)){
                if (maxRunEntry == null || currentEntry.getValue().getRuns() > maxRunEntry.getValue().getRuns()) {
                    maxRunEntry = currentEntry;
                }
                if (maxWitcketEntry == null || currentEntry.getValue().getWitckets() > maxWitcketEntry.getValue().getWitckets()) {
                    maxWitcketEntry = currentEntry;
                }
            }
        }
        logger.info("Highest Run and Witcket:");
        String highestRunScorer = maxRunEntry.getValue().getName();
        String highestWitcketTaker = maxWitcketEntry.getValue().getName();
        logger.info("Highest Run Scorer : {}", highestRunScorer);
        logger.info("Highest Witcket Taker : {}", highestWitcketTaker);
    }

    public static void top3RunScorerAndTop3WitcketTaker(){
        logger.info("Top 3 Run Scorer :");
        List<Player> top3Batter = bowlerToPlayer.values().stream().sorted((player, t1) -> Double.compare(t1.getRuns(),player.getRuns())).limit(3).toList();
        top3Batter.forEach(Player::printPlayerDetails);

        logger.info("Top 3 Witcket Taker :");
        List<Player> top3Bowler = bowlerToPlayer.values().stream().sorted((player, t1) -> Double.compare(t1.getWitckets(),player.getWitckets())).limit(3).toList();
        top3Bowler.forEach(Player::printPlayerDetails);
    }

    public static void addMatchFixture(String filePath)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<>();
            // only takind two input for now but can we increased further.
            int min = 1;
            int max = 8;
            int noOfMatches = 2;
            int currNoOfMatches = 0;
            ArrayList<String> arr = new ArrayList<>();
            arr.add("MI");
            arr.add("CSK");
            arr.add("KKR");
            arr.add("RR");
            arr.add("RCB");
            arr.add("PUN");
            arr.add("SRH");
            arr.add("DC");
            data.add(new String[] { "Date", "Match Number", "Team Home", "Team Away", "Ground" });
            while(currNoOfMatches < noOfMatches){
                int a = (int)(Math.random()*(max-min+1)+min);
                int b = (int)(Math.random()*(max-min+1)+min);
                int c = (int)(Math.random()*(max-min+1)+min);

                if(check(a,b,c)){
                    String teama = arr.get(a);
                    String teamb = arr.get(b);
                    String ground = arr.get(c);
                    data.add(new String[] { "16/01/2015", 1, teama, teamb, ground });
                    currNoOfMatches++;
                }
            }
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean check(int a, int b, int c){
        if(a == b)return false;
        if(c != a && c != b)return false;
        return true;
    }

}