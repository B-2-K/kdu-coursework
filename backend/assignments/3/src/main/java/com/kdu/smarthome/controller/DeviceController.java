package com.kdu.smarthome.controller;

import com.kdu.smarthome.Utility.ParseValidator;
import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.dto.request.DeviceRequestDTO;
import com.kdu.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling device-related operations.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;

    /**
     * Constructor for DeviceController.
     *
     * @param deviceService The service responsible for device operations.
     */
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Endpoint for registering a new device.
     *
     * @param deviceRequestDTO The DTO containing information for registering a device.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping("/register")
    public ResponseEntity<SharedResponseDTO> registerDevice(@RequestBody DeviceRequestDTO deviceRequestDTO) {
        return new ResponseEntity<>(deviceService.registerDevice(deviceRequestDTO), HttpStatus.OK);
    }

    /**
     * Endpoint for adding a new device.
     *
     * @param deviceDTO The DTO containing information for adding a device.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping("/add")
    public ResponseEntity<SharedResponseDTO> addDevice(@RequestBody DeviceDTO deviceDTO) {
        if (!ParseValidator.isParsable(deviceDTO.getHouseId()) || !ParseValidator.isParsable(deviceDTO.getRoomId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deviceService.addDevice(deviceDTO), HttpStatus.OK);
    }
}
