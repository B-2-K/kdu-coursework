package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param vehicleService The service responsible for handling vehicle-related operations.
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Handles GET requests to retrieve a list of all vehicles.
     *
     * @return ResponseEntity containing a list of VehicleDTOs.
     */
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Handles GET requests to retrieve a specific vehicle by ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return ResponseEntity containing the VehicleDTO representing the specified vehicle.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        VehicleDTO vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    /**
     * Handles POST requests to add a new vehicle.
     *
     * @param vehicle The Vehicle entity to be added.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    /**
     * Handles PUT requests to update an existing vehicle.
     *
     * @param id             The ID of the vehicle to update.
     * @param updatedVehicle The updated Vehicle entity.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        vehicleService.updateVehicle(id, updatedVehicle);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    /**
     * Handles DELETE requests to delete a specific vehicle by ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
