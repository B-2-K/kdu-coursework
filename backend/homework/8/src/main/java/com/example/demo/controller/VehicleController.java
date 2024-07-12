package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to vehicles.
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * Constructor for VehicleController.
     *
     * @param vehicleService The service responsible for business logic related to vehicles.
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Handles GET request to retrieve a list of all vehicles.
     *
     * @return ResponseEntity containing a list of vehicles.
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Handles GET request to retrieve a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return ResponseEntity containing the retrieved vehicle or not found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Handles POST request to add a new vehicle.
     *
     * @param vehicle The vehicle to be added.
     * @return ResponseEntity with a status of CREATED if successful.
     */
    @PostMapping
    public ResponseEntity<Void> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Handles PUT request to update an existing vehicle.
     *
     * @param id            The ID of the vehicle to update.
     * @param updatedVehicle The updated information for the vehicle.
     * @return ResponseEntity with a status of OK if successful.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        vehicleService.updateVehicle(id, updatedVehicle);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles DELETE request to delete a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return ResponseEntity with a status of NO CONTENT if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Handles GET request to retrieve the most expensive vehicle.
     *
     * @return ResponseEntity containing the most expensive vehicle or not found status.
     */
    @GetMapping("/most-expensive")
    public ResponseEntity<Vehicle> getMostExpensiveVehicle() {
        return vehicleService.getMostExpensiveVehicle()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Handles GET request to retrieve the least expensive vehicle.
     *
     * @return ResponseEntity containing the least expensive vehicle or not found status.
     */
    @GetMapping("/least-expensive")
    public ResponseEntity<Vehicle> getLeastExpensiveVehicle() {
        return vehicleService.getLeastExpensiveVehicle()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
