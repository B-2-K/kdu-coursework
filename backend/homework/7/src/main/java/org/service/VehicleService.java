package org.service;

import jakarta.annotation.PostConstruct;
import org.service.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.utility.Vehicle;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing vehicles.
 */
@Component
@Scope("prototype")
@ComponentScan
public class VehicleService {
    public static final List<Vehicle> inventory = new ArrayList<>();
    private Factory factory;
    private TyreService tyreService;
    private SpeakerService speakerService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VehicleService.class);
    public List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Constructor for VehicleService.
     *
     * @param tyreService    The TyreService dependency.
     * @param speakerService The SpeakerService dependency.
     */
    @Autowired
    public VehicleService(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
    }

    /**
     * Setter method for setting the factory using Spring's @Autowired and @Qualifier annotations.
     *
     * @param factory The Factory to be set.
     */
    @Autowired
    public void setFactory(@Qualifier("Bangalore") Factory factory) {
        this.factory = factory;
    }

    /**
     * Method annotated with @PostConstruct, called after the bean has been initialized.
     * Adds vehicles to the list after initialization.
     */
    @PostConstruct
    public void addVehicle() {
        vehicles.add(new Vehicle("vehicle1", speakerService.boseSpeaker(), tyreService.bridgestoneTyre()));
        vehicles.add(new Vehicle("vehicle2", speakerService.sonySpeaker(), tyreService.mrfTyre()));
    }

    /**
     * Updates the cost of each vehicle by adding additional cost.
     *
     * @param additionalCost The additional cost to be added to each vehicle.
     */
    public void updateCost(double additionalCost) {
        for (Vehicle vehicle : vehicles) {
            vehicle.setPrice(vehicle.getPrice() + additionalCost);
        }
    }

    /**
     * Prints details of all vehicles.
     */
    public void printVehiclesDetails() {
        for (Vehicle vehicle : vehicles) {
            inventory.add(vehicle);
            printVehicleDetails(vehicle);
        }
    }

    /**
     * Prints details of the most expensive vehicle.
     */
    public void printMostExpensiveVehicle() {
        double price = 0.0;
        Vehicle mostExpensiveVehicle = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() > price) {
                price = vehicle.getPrice();
                mostExpensiveVehicle = vehicle;
            }
        }
        if (mostExpensiveVehicle == null) {
            logger.info("No Vehicle Found");
        } else {
            logger.info("Most Expensive Vehicle Details : ");
            printVehicleDetails(mostExpensiveVehicle);
        }
    }

    /**
     * Prints details of a specific vehicle.
     *
     * @param vehicle The vehicle whose details are to be printed.
     */
    public void printVehicleDetails(Vehicle vehicle) {
        logger.info("Name of vehicle: {}", vehicle.getName());
        logger.info("Tyre of vehicle: {}", vehicle.getTyre().getBrand());
        logger.info("Speaker of vehicle: {}", vehicle.getSpeaker().getBrand());
        logger.info("Price of vehicle: {}", vehicle.getPrice());
    }
}
