package com.kdu.smarthome.controller;

import com.kdu.smarthome.Utility.ParseValidator;
import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.services.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling room-related operations.
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    /**
     * Constructor for RoomController.
     *
     * @param roomService The service responsible for room-related operations.
     */
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint for adding a new room to a house.
     *
     * @param houseId      The ID of the house to which the room will be added.
     * @param roomDTO  The DTO containing the name of the room to be added.
     * @param request      The HTTP request.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoom(@RequestParam String houseId, @RequestBody RoomDTO roomDTO, HttpServletRequest request) {
        if (!ParseValidator.isParsable(houseId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(roomService.addRoom(Long.parseLong(houseId), roomDTO.getRoomName()), HttpStatus.OK);
    }
}
