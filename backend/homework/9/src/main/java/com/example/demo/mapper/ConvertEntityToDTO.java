package com.example.demo.mapper;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting entities to DTOs (Data Transfer Objects).
 */
public class ConvertEntityToDTO {

    /**
     * Converts a single Vehicle entity to a VehicleDTO.
     *
     * @param vehicle The Vehicle entity to be converted.
     * @return The corresponding VehicleDTO.
     */
    public static VehicleDTO convertEntityToDTO(Vehicle vehicle) {
        return new VehicleDTO(vehicle.getId(), vehicle.getName(), vehicle.getPrice());
    }

    /**
     * Converts a list of Vehicle entities to a list of VehicleDTOs.
     *
     * @param vehicles The list of Vehicle entities to be converted.
     * @return The corresponding list of VehicleDTOs.
     */
    public static List<VehicleDTO> convertEntitiesToDTOs(List<Vehicle> vehicles) {
        return vehicles.stream().map(ConvertEntityToDTO::convertEntityToDTO).collect(Collectors.toList());
    }
}
