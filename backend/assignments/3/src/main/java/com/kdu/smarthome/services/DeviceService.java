package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.dto.request.DeviceRequestDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exceptions.customexceptions.IncorrectUsernamePasswordException;
import com.kdu.smarthome.exceptions.customexceptions.NotFoundException;
import com.kdu.smarthome.mapping.Mapper;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing devices in a smart home.
 */
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final InventoryRepository inventoryRepository;
    private final Mapper mapper;
    private final HouseRepository houseRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, InventoryRepository inventoryRepository, Mapper mapper, HouseRepository houseRepository) {
        this.deviceRepository = deviceRepository;
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
        this.houseRepository = houseRepository;
    }

    /**
     * Registers a device using valid credentials.
     *
     * @param deviceRequestDTO The DTO containing device registration information.
     * @return The response information after registering the device.
     */
    public SharedResponseDTO registerDevice(DeviceRequestDTO deviceRequestDTO) {
        Optional<Inventory> optionalInventory = findInventoryByKickstonId(deviceRequestDTO.getKickstonId(), deviceRequestDTO.getDeviceUsername());
        Inventory inventory = optionalInventory.orElseThrow(() -> new NotFoundException("Device not found in inventory!"));
        if (deviceRequestDTO.getDevicePassword().equals(inventory.getDevicePassword())) {
            Device device = mapper.deviceMapping(deviceRequestDTO);
            deviceRepository.save(device);
            return new SharedResponseDTO("Device registered successfully!", "Kickston id : " + device.getKickstonId(), HttpStatus.OK);
        } else {
            throw new IncorrectUsernamePasswordException("Incorrect password!");
        }
    }

    /**
     * Adds a device to the specified room and house.
     *
     * @param deviceDTO The DTO containing information to add a device to a room.
     * @return The response information after adding the device.
     */
    public SharedResponseDTO addDevice(DeviceDTO deviceDTO) {
        Optional<Device> optionalDevice = deviceRepository.findByKickstonId(deviceDTO.getKickstonId());
        Optional<House> optionalHouse = houseRepository.findById(Long.parseLong(deviceDTO.getHouseId()));
        if (optionalDevice.isEmpty() || optionalHouse.isEmpty()) {
            throw new NotFoundException("House and Device with given id not found!");
        }
        House house = optionalHouse.get();
        Device device = optionalDevice.get();
        Long roomId = Long.parseLong(deviceDTO.getRoomId());
        Room room = findRoomById(house, roomId);
        device.setRoom(room);
        deviceRepository.save(device);
        return new SharedResponseDTO("Device added successfully!", "Room: " + deviceDTO.getRoomId(), HttpStatus.OK);
    }

    private Optional<Inventory> findInventoryByKickstonId(String kickstonId, String deviceUsername) {
        return inventoryRepository.findByKickstonId(kickstonId)
                .filter(inventory -> inventory.getDeviceUsername().equals(deviceUsername));
    }

    private Room findRoomById(House house, Long roomId) {
        return house.getRooms().stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Room with the given id not found!"));
    }
}
