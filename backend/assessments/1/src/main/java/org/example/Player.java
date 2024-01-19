package org.example;

import org.slf4j.LoggerFactory;

public class Player {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Player.class);

    private String name;
    private String team;
    private String role;
    private int mathces;
    private int runs;
    private double average;
    private double strikeRate;
    private int witckets;

    public Player(String name, String team, String role, int mathces, int runs, double average, double strikeRate, int witckets) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.mathces = mathces;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.witckets = witckets;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getRole() {
        return role;
    }

    public int getMathces() {
        return mathces;
    }

    public int getRuns() {
        return runs;
    }

    public double getAverage() {
        return average;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public int getWitckets() {
        return witckets;
    }

    public void printPlayerDetails() {
        logger.info("Name : {}", name);
    }
}
