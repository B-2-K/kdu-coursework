package com.kdu.smarthome.mapping;

import com.kdu.smarthome.dto.request.DeviceRequestDTO;
import com.kdu.smarthome.dto.request.HouseRequestDTO;
import com.kdu.smarthome.dto.request.InventoryRequestDTO;
import com.kdu.smarthome.dto.request.UserRequestDTO;
import com.kdu.smarthome.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class provides mapping functions to convert DTOs (Data Transfer Objects)
 * to corresponding entity models in the Smart Home application.
 */
@Component
public class Mapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Maps a UserRequestDTO to a UserModel.
     *
     * @param userRequestDTO The UserRequestDTO containing user information.
     * @return A UserModel with mapped properties.
     */
    public UserModel userMapping(UserRequestDTO userRequestDTO){
        UserModel userModel = new UserModel();
        userModel.setUsername(userRequestDTO.getUsername());
        userModel.setFirstName(userRequestDTO.getFirstName());
        userModel.setLastName(userRequestDTO.getLastName());
        userModel.setEmail(userRequestDTO.getEmail());
        userModel.setName(userRequestDTO.getName());
        userModel.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return userModel;
    }

    /**
     * Maps a HouseRequestDTO and a UserModel to a House entity.
     *
     * @param houseRequestDTO The HouseRequestDTO containing house information.
     * @param userModel The UserModel associated with the house.
     * @return A House entity with mapped properties.
     */
    public House houseMapping(HouseRequestDTO houseRequestDTO, UserModel userModel){
        House house = new House();
        house.setHouseName(houseRequestDTO.getHouseName());
        house.setAddress(houseRequestDTO.getAddress());
        house.getUsersList().add(userModel);
        return house;
    }

    /**
     * Maps a DeviceRequestDTO to a Device entity.
     *
     * @param deviceRequestDTO The DeviceRequestDTO containing device information.
     * @return A Device entity with mapped properties.
     */
    public Device deviceMapping(DeviceRequestDTO deviceRequestDTO){
        Device device = new Device();
        device.setDeviceUsername(deviceRequestDTO.getDeviceUsername());
        device.setDevicePassword(deviceRequestDTO.getDevicePassword());
        device.setKickstonId(deviceRequestDTO.getKickstonId());
        return device;
    }

    /**
     * Maps an InventoryRequestDTO to an Inventory entity.
     *
     * @param inventoryRequestDTO The InventoryRequestDTO containing inventory information.
     * @return An Inventory entity with mapped properties.
     */
    public Inventory inventoryMapping(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory = new Inventory();
        inventory.setKickstonId(inventoryRequestDTO.getKickstonId());
        inventory.setDeviceUsername(inventoryRequestDTO.getDeviceUsername());
        inventory.setDevicePassword(inventoryRequestDTO.getDevicePassword());
        inventory.setManufactureDateTime(inventoryRequestDTO.getManufactureDateTime());
        inventory.setManufactureFactoryPlace(inventoryRequestDTO.getManufactureFactoryPlace());

        return inventory;
    }

    /**
     * Maps a roomName and a House to a Room entity.
     *
     * @param roomName The name of the room.
     * @param house The House entity to which the room belongs.
     * @return A Room entity with mapped properties.
     */
    public Room roomMapping(String roomName, House house){
        Room room = new Room();
        room.setRoomName(roomName);
        room.setHouse(house);
        return room;
    }
}
