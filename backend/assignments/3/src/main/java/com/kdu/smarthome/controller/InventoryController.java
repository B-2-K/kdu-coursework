package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.request.InventoryRequestDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling inventory-related operations.
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Constructor for InventoryController.
     *
     * @param inventoryService The service responsible for inventory-related operations.
     */
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Endpoint for adding an item to the inventory.
     *
     * @param inventoryRequestDTO The DTO containing information for adding an item.
     * @return ResponseEntity containing the response information and HTTP status.
     */
    @PostMapping
    public ResponseEntity<SharedResponseDTO> addItem(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return new ResponseEntity<>(inventoryService.addItem(inventoryRequestDTO), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving items from the inventory.
     *
     * @return ResponseEntity containing the response information and HTTP status.
     * @throws JsonProcessingException if there is an issue processing JSON.
     */
    @GetMapping
    public ResponseEntity<InventoryResponseDTO> getItem() throws JsonProcessingException {
        return new ResponseEntity<>(inventoryService.getItems(), HttpStatus.OK);
    }
}
