package org.service;

import jakarta.annotation.PostConstruct;
import org.beans.Vehicle;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({TyreService.class, SpeakerService.class})
public class VehicleService {
    @Autowired
    private TyreService tyreService;
    @Autowired
    private SpeakerService speakerService;
    public static List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VehicleService.class);
    @PostConstruct
    public void init(){
        vehicles.add(new Vehicle("vehicle1", speakerService.boseSpeaker(), tyreService.bridgestoneTyre()));
        vehicles.add(new Vehicle("vehicle2", speakerService.sonySpeaker(), tyreService.mrfTyre()));
        vehicles.add(new Vehicle("vehicle3", speakerService.boseSpeaker(), tyreService.mrfTyre()));
        vehicles.add(new Vehicle("vehicle3", speakerService.sonySpeaker(), tyreService.mrfTyre()));
    }

    public static void printVehiclesDetails(){
        for(Vehicle vehicle : vehicles){
            printVehicleDetails(vehicle);
        }
    }

    public static void printMostExpensiveVehicle(){
        double price = 0.0;
        Vehicle mostExpensiveVehicle = null;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getPrice() > price){
                price = vehicle.getPrice();
                mostExpensiveVehicle = vehicle;
            }
        }
        if(mostExpensiveVehicle == null){
            logger.info("No Vehicle Found");
        }
        else {
            logger.info("Most Expensive Vehicle Details : ");
            printVehicleDetails(mostExpensiveVehicle);
        }
    }

    public static void printVehicleDetails(Vehicle vehicle){
        logger.info("Name of vehicle: {}", vehicle.getName());
        logger.info("Tyre of vehicle: {}", vehicle.getTyre().getBrand());
        logger.info("Speaker of vehicle: {}", vehicle.getSpeaker().getBrand());
        logger.info("Price of vehicle: {}", vehicle.getPrice());
    }
}
