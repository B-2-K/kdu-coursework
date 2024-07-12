package org.app;

import org.config.AppConfig;
import org.service.VehicleService;
import org.service.factory.Factory;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.utility.Vehicle;
import static org.service.VehicleService.inventory;

public class Main {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public static void main(String[] args) {
        /**
         * Create the application context
         */
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        /**
         Get the beans from the context with @Qualifier
         Get the VehicleService beans from the context
         Set the factories for each VehicleService
         */

        Factory factoryBangalore = context.getBean("Bangalore", Factory.class);
        VehicleService vehicleServiceBangalore = context.getBean(VehicleService.class);

        /**
        Set the factory to bangalore
        Update the cost of the vehicle by adding transportation cost
         */

        vehicleServiceBangalore.setFactory(factoryBangalore);
        vehicleServiceBangalore.updateCost(factoryBangalore.addTransportationCost());

        /**
        Print vehicle details for Bangalore
        Print most expensive vehicle in Bangalore
         */

        logger.info("Vehicles in Bangalore:");
        vehicleServiceBangalore.printVehiclesDetails();
        logger.info("Most expensive vehicle in Bangalore:");
        vehicleServiceBangalore.printMostExpensiveVehicle();

        logger.info("________________________________________________________________");

        /**
         Get the beans from the context with @Qualifier
         Get the VehicleService beans from the context
         Set the factories for each VehicleService
         */

        Factory factoryChennai = context.getBean("Chennai", Factory.class);
        VehicleService vehicleServiceChennai = context.getBean(VehicleService.class);

        /**
        Set the factory to Chennai
        Update the cost of the vehicle by adding transportation cost
         */

        vehicleServiceChennai.setFactory(factoryChennai);
        vehicleServiceChennai.updateCost(factoryChennai.addTransportationCost());

         /**
        Print vehicle details for Chennai
        Print most expensive vehicle in Bangalore
         */

        logger.info("Vehicles in Chennai:");
        vehicleServiceChennai.printVehiclesDetails();
        logger.info("Most expensive vehicle in Chennai:");
        vehicleServiceChennai.printMostExpensiveVehicle();

        /**
         * Print all the vehicle in the inventory
         */
        logger.info("________________________________________________________________");
        logger.info("Vehicles in inventory:");
        printInventory();

        /**
         * Close the context
         */
        context.close();
    }

    /**
     * Function to print all the vehicle in the inventory
     */
    public static void printInventory() {
        for (Vehicle vehicle : inventory) {
            logger.info("Name of vehicle: {}", vehicle.getName());
            logger.info("Tyre of vehicle: {}", vehicle.getTyre().getBrand());
            logger.info("Speaker of vehicle: {}", vehicle.getSpeaker().getBrand());
            logger.info("Price of vehicle: {}", vehicle.getPrice());
        }
    }
}
