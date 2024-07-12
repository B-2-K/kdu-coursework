package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Utility.ParseValidator;
import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.dto.request.HouseRequestDTO;
import com.kdu.smarthome.dto.response.HouseResponseDTO;
import com.kdu.smarthome.dto.RoomDevicesDTO;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.services.HouseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling house-related operations.
 */
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;

    /**
     * Constructor for HouseController.
     *
     * @param houseService The service responsible for house-related operations.
     */
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Endpoint for adding a new house.
     *
     * @param houseRequestDTO The DTO containing information for adding a house.
     * @param request         The HTTP request containing the Authorization header.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping
    public ResponseEntity<HouseResponseDTO> addHouse(@RequestBody HouseRequestDTO houseRequestDTO,
                                                     HttpServletRequest request) {
        return new ResponseEntity<>(houseService.addHouse(houseRequestDTO, extractToken(request)), HttpStatus.OK);
    }

    /**
     * Endpoint for adding a user to a specific house.
     *
     * @param houseId  The ID of the house to which the user will be added.
     * @param username The DTO containing the username to be added.
     * @param request  The HTTP request containing the Authorization header.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<SharedResponseDTO> addUser(@PathVariable String houseId,
                                                     @RequestBody UserDTO username,
                                                     HttpServletRequest request) {
        return new ResponseEntity<>(houseService.addUser(parseHouseId(houseId), username.getUsername(), extractToken(request)), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a list of all houses.
     *
     * @return ResponseEntity containing the list of houses and HTTP status.
     * @throws JsonProcessingException if there is an issue processing JSON.
     */
    @GetMapping
    public ResponseEntity<HouseDTO> getHouses() throws JsonProcessingException {
        return new ResponseEntity<>(houseService.getAll(), HttpStatus.OK);
    }

    /**
     * Endpoint for updating the address of a specific house.
     *
     * @param houseId     The ID of the house to update.
     * @param newAddress  The new address for the house.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PutMapping
    public ResponseEntity<SharedResponseDTO> updateAddress(@RequestParam String houseId, @RequestBody String newAddress) {
        if (!ParseValidator.isParsable(houseId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(houseService.updateAddress(parseHouseId(houseId), newAddress), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving information about rooms and devices in a specific house.
     *
     * @param houseId The ID of the house to retrieve information for.
     * @return ResponseEntity containing the information about rooms and devices and HTTP status.
     * @throws JsonProcessingException if there is an issue processing JSON.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<RoomDevicesDTO> getRoomsDevices(@PathVariable String houseId) throws JsonProcessingException {
        return new ResponseEntity<>(houseService.getRoomsDevices(parseHouseId(houseId)), HttpStatus.OK);
    }

    /**
     * Extracts the token from the Authorization header in the HTTP request.
     *
     * @param request The HTTP request.
     * @return The extracted token.
     */
    private String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }

    /**
     * Parses the house ID from a string to a long.
     *
     * @param houseId The string representation of the house ID.
     * @return The parsed house ID as a long.
     */
    private long parseHouseId(String houseId) {
        return Long.parseLong(houseId);
    }
}
