package com.example.demo.service;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;
import com.example.demo.mapper.ConvertEntityToDTO;
import com.example.demo.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Vehicle entities and DTO conversions.
 */
@Service
public class VehicleService {

    /**
     * Retrieves a list of all vehicles and converts them to DTOs.
     *
     * @return The list of VehicleDTOs representing all vehicles.
     */
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = VehicleRepository.getAllVehicles();
        return ConvertEntityToDTO.convertEntitiesToDTOs(vehicles);
    }

    /**
     * Retrieves a specific vehicle by ID and converts it to a DTO.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return The VehicleDTO representing the specified vehicle.
     */
    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = VehicleRepository.getVehicleById(id);
        return ConvertEntityToDTO.convertEntityToDTO(vehicle);
    }

    /**
     * Adds a new vehicle to the repository.
     *
     * @param vehicle The Vehicle entity to be added.
     */
    public void addVehicle(Vehicle vehicle) {
        VehicleRepository.addVehicle(vehicle);
    }

    /**
     * Updates an existing vehicle with the provided ID using the data from the updatedVehicle.
     *
     * @param id             The ID of the vehicle to update.
     * @param updatedVehicle The updated Vehicle entity.
     */
    public void updateVehicle(Long id, Vehicle updatedVehicle) {
        VehicleRepository.updateVehicle(id, updatedVehicle);
    }

    /**
     * Deletes a vehicle with the specified ID from the repository.
     *
     * @param id The ID of the vehicle to delete.
     */
    public void deleteVehicle(Long id) {
        VehicleRepository.deleteVehicle(id);
    }
}
