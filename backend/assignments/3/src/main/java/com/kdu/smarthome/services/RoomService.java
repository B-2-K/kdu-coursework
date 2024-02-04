package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exceptions.customexceptions.NotFoundException;
import com.kdu.smarthome.mapping.Mapper;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing rooms in a smart home.
 */
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final Mapper mapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository, Mapper mapper) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.mapper = mapper;
    }

    /**
     * Adds a room to a particular house using the house ID.
     *
     * @param id       The ID of the house.
     * @param roomName The name of the room to be added.
     * @return The response containing information about the added room.
     */
    public RoomResponseDTO addRoom(Long id, String roomName) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            Room room = mapper.roomMapping(roomName, house);
            roomRepository.save(room);
            return new RoomResponseDTO("Room added successfully!", room, HttpStatus.OK);
        } else {
            throw new NotFoundException("House with given id not found!");
        }
    }
}
