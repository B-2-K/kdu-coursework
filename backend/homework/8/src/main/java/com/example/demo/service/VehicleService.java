package com.example.demo.service;

import com.example.demo.entity.Vehicle;
import com.example.demo.exception.DuplicateVehicleException;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.repository.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic related to vehicles.
 */
@Service
public class VehicleService {

    /**
     * List of vehicles managed by the service.
     */
    public static final List<Vehicle> vehicles = Inventory.vehicles;

    /**
     * Variable to keep track of the next available ID for new vehicles.
     */
    private Long nextId = 1L;

    /**
     * Retrieves a list of all vehicles.
     *
     * @return List of vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return An Optional containing the vehicle with the given ID, or empty if not found.
     */
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    /**
     * Adds a new vehicle to the list.
     *
     * @param vehicle The vehicle to be added.
     * @throws DuplicateVehicleException If a vehicle with the same ID already exists.
     */
    public void addVehicle(Vehicle vehicle) {
        if (vehicles.stream().anyMatch(v -> v.getId().equals(vehicle.getId()))) {
            throw new DuplicateVehicleException();
        }
        vehicle.setId(nextId++);
        vehicles.add(vehicle);
    }

    /**
     * Updates an existing vehicle with new information.
     *
     * @param id             The ID of the vehicle to update.
     * @param updatedVehicle The updated information for the vehicle.
     * @throws VehicleNotFoundException If the vehicle with the given ID is not found.
     */
    public void updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = getVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException());

        vehicle.setName(updatedVehicle.getName());
        vehicle.setPrice(updatedVehicle.getPrice());
    }

    /**
     * Deletes a vehicle with the specified ID.
     *
     * @param id The ID of the vehicle to delete.
     * @throws VehicleNotFoundException If the vehicle with the given ID is not found.
     */
    public void deleteVehicle(Long id) {
        if (!vehicles.removeIf(vehicle -> vehicle.getId().equals(id))) {
            throw new VehicleNotFoundException();
        }
    }

    /**
     * Retrieves the most expensive vehicle.
     *
     * @return An Optional containing the most expensive vehicle, or empty if the list is empty.
     * @throws VehicleNotFoundException If the list of vehicles is empty.
     */
    public Optional<Vehicle> getMostExpensiveVehicle() {
        if (vehicles.isEmpty()) {
            throw new VehicleNotFoundException();
        }

        return vehicles.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()));
    }

    /**
     * Retrieves the least expensive vehicle.
     *
     * @return An Optional containing the least expensive vehicle, or empty if the list is empty.
     * @throws VehicleNotFoundException If the list of vehicles is empty.
     */
    public Optional<Vehicle> getLeastExpensiveVehicle() {
        if (vehicles.isEmpty()) {
            throw new VehicleNotFoundException();
        }

        return vehicles.stream()
                .min((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()));
    }
}
