package com.example.demo.repository;

import com.example.demo.entity.Vehicle;
import com.example.demo.exception.DuplicateVehicleException;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.logger.CustomLogger;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    public static final List<Vehicle> vehicles = new ArrayList<>();
    public static List<Vehicle> getAllVehicles() {
        CustomLogger.info("Getting all vehicles");
        return vehicles;
    }

    public static Vehicle getVehicleById(Long id) {
        CustomLogger.info("Getting vehicle with id " + id);
        Vehicle currVehicle = vehicles.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (currVehicle == null) {
            CustomLogger.error("Vehicle with id " + id + " not found");
            throw new VehicleNotFoundException();
        }
        CustomLogger.info("Vehicle with id " + id + " found");
        return currVehicle;
    }

    public static void addVehicle(Vehicle vehicle) {
        CustomLogger.info("Adding vehicle with id " + vehicle.getId());
        if (vehicles.stream().anyMatch(v -> v.getId().equals(vehicle.getId()))) {
            CustomLogger.warn("Vehicle with id " + vehicle.getId() + " already exists");
            throw new DuplicateVehicleException();
        }
        CustomLogger.info("Vehicle with id " + vehicle.getId() + " added");
        vehicles.add(vehicle);
    }

    public static void updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = getVehicleById(id);

        if (vehicle == null) {
            CustomLogger.error("Vehicle with id " + id + " doesn't exists");
            throw new VehicleNotFoundException();
        }

        vehicle.setName(updatedVehicle.getName());
        vehicle.setPrice(updatedVehicle.getPrice());
        CustomLogger.info("Vehicle with id " + id + " updated");
    }

    public static void deleteVehicle(Long id) {
        CustomLogger.info("Deleting vehicle with id " + id);
        if (!vehicles.removeIf(vehicle -> vehicle.getId().equals(id))) {
            CustomLogger.error("Vehicle with id " + id + " doesn't exists");
            throw new VehicleNotFoundException();
        }
        CustomLogger.info("Vehicle with id " + id + " deleted");
    }
}
