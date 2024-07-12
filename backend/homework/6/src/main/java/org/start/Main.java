package org.start;

import org.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Create the application context
        var context = new AnnotationConfigApplicationContext(VehicleService.class);
        // Print vehicle details
        VehicleService.printVehiclesDetails();
        // print most expensive vehicle details
        VehicleService.printMostExpensiveVehicle();
        // Close the context
        context.close();
    }
}
