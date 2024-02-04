package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Utility.ListToJSON;
import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.request.InventoryRequestDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.mapping.Mapper;
import com.kdu.smarthome.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing the inventory of items.
 */
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final Mapper mapper;
    private final ListToJSON listToJSON;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, Mapper mapper, ListToJSON listToJSON) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
        this.listToJSON = listToJSON;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param inventoryRequestDTO The DTO containing inventory item details.
     * @return The response containing information about the added item.
     */
    public SharedResponseDTO addItem(InventoryRequestDTO inventoryRequestDTO) {
        Inventory inventory = mapper.inventoryMapping(inventoryRequestDTO);
        inventoryRepository.save(inventory);
        return new SharedResponseDTO("Item added successfully!", "Kickston ID : " + inventory.getKickstonId(), HttpStatus.OK);
    }

    /**
     * Fetches all the items from the inventory.
     *
     * @return The response containing a JSON representation of all inventory items.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    public InventoryResponseDTO getItems() throws JsonProcessingException {
        List<Inventory> inventories = inventoryRepository.findAll();
        String inventoryJSON = listToJSON.convertListToJSONString(inventories);
        return new InventoryResponseDTO(inventoryJSON, HttpStatus.OK);
    }
}
