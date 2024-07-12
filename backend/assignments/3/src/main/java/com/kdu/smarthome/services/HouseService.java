package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Utility.ListToJSON;
import com.kdu.smarthome.Utility.ObjectToJSON;
import com.kdu.smarthome.Utility.TokenDecoder;
import com.kdu.smarthome.mapping.Mapper;
import com.kdu.smarthome.dto.response.SharedResponseDTO;
import com.kdu.smarthome.dto.response.MiscResponseDTO;
import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.dto.request.HouseRequestDTO;
import com.kdu.smarthome.dto.response.HouseResponseDTO;
import com.kdu.smarthome.dto.RoomDevicesDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.exceptions.customexceptions.NotFoundException;
import com.kdu.smarthome.exceptions.customexceptions.UserNotAdminException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing houses in a smart home.
 */
@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final TokenDecoder tokenDecoder;
    private final ObjectToJSON objectToJSON;
    private final ListToJSON listToJSON;

    @Autowired
    public HouseService(HouseRepository houseRepository, UserRepository userRepository, Mapper mapper,
                        TokenDecoder tokenDecoder, ObjectToJSON objectToJSON, ListToJSON listToJSON) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.tokenDecoder = tokenDecoder;
        this.objectToJSON = objectToJSON;
        this.listToJSON = listToJSON;
    }

    /**
     * Adds a house to the user associated with the token.
     *
     * @param houseRequestDTO The DTO containing house details.
     * @param token            The user's authentication token.
     * @return The response containing the added house details.
     */
    public HouseResponseDTO addHouse(HouseRequestDTO houseRequestDTO, String token) {
        String username = decodeTokenAndGetUsername(token);
        UserModel user = getUserByUsername(username);
        setAdminRole(user);
        House house = mapDTOAndSaveToRepository(houseRequestDTO, user);

        return new HouseResponseDTO("House added successfully!", house, HttpStatus.OK);
    }

    /**
     * Adds a user to the house. Can only be added by an admin.
     *
     * @param id       The ID of the house.
     * @param username The username of the user to be added.
     * @param token    The admin's authentication token.
     * @return The response containing information about the added user.
     */
    public SharedResponseDTO addUser(Long id, String username, String token) {
        String userAdmin = decodeTokenAndGetUsername(token);
        UserModel adminUser = getUserByUsername(userAdmin);
        validateAdminRole(adminUser);

        House house = getHouseById(id);
        UserModel userToAdd = getUserByUsername(username);
        addUsertoHouse(house, userToAdd);

        return new SharedResponseDTO("User added successfully!", "Username : " + username, HttpStatus.OK);
    }

    /**
     * Fetches all the houses.
     *
     * @return The response containing a list of houses.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    public HouseDTO getAll() throws JsonProcessingException {
        List<House> houseList = getAllHouses();
        String houses = convertHouseListToJSONString(houseList);

        return new HouseDTO("Fetched Successfully!", houses, HttpStatus.OK);
    }

    /**
     * Updates the address of a house.
     *
     * @param id         The ID of the house.
     * @param newAddress The new address for the house.
     * @return The response containing information about the updated house.
     */
    public SharedResponseDTO updateAddress(Long id, String newAddress) {
        House house = getHouseById(id);
        updateHouseAddress(house, newAddress);

        return new SharedResponseDTO("House updated successfully!", "New Address : " + newAddress, HttpStatus.OK);
    }

    /**
     * Get details of the house along with its rooms and devices.
     *
     * @param id The ID of the house.
     * @return The response containing information about the house, its rooms, and devices.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    public RoomDevicesDTO getRoomsDevices(Long id) throws JsonProcessingException {
        House house = getHouseById(id);
        List<Room> rooms = getRoomsFromHouse(house);
        List<Device> devices = getDevicesFromRooms(rooms);
        MiscResponseDTO miscResponseDTO = createTempRespDTO(house, rooms, devices);
        String roomsDevices = convertTempRespDTOToJSONString(miscResponseDTO);

        return new RoomDevicesDTO("Fetched successfully!", roomsDevices, HttpStatus.OK);
    }

    // Helper functions

    /**
     * Decode the authentication token and get the associated username.
     *
     * @param token The authentication token.
     * @return The username associated with the token.
     */
    private String decodeTokenAndGetUsername(String token) {
        return tokenDecoder.decodeToken(token);
    }

    /**
     * Get a user from the repository based on the provided username.
     *
     * @param username The username of the user.
     * @return The User object.
     * @throws NotFoundException If the user is not found in the repository.
     */
    private UserModel getUserByUsername(String username) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return optionalUser.get();
    }

    /**
     * Set the admin role for a given user.
     *
     * @param user The user for whom the role should be set to admin.
     */
    private void setAdminRole(UserModel user) {
        user.setRole("ROLE_ADMIN");
    }

    /**
     * Map the HouseRequestDTO to a House entity, save it to the repository, and return the created house.
     *
     * @param houseRequestDTO The DTO containing house details.
     * @param user            The user associated with the house.
     * @return The created House entity.
     */
    private House mapDTOAndSaveToRepository(HouseRequestDTO houseRequestDTO, UserModel user) {
        House house = mapper.houseMapping(houseRequestDTO, user);
        houseRepository.save(house);
        return house;
    }

    /**
     * Validate if the given user has the admin role.
     *
     * @param user The user to be validated.
     * @throws UserNotAdminException If the user is not an admin.
     */
    private void validateAdminRole(UserModel user) {
        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new UserNotAdminException("Given user is not an admin!");
        }
    }

    /**
     * Get a house from the repository based on the provided ID.
     *
     * @param id The ID of the house.
     * @return The House object.
     * @throws NotFoundException If the house with the given ID is not found in the repository.
     */
    private House getHouseById(Long id) {
        Optional<House> optionalHouse = houseRepository.findById(id);

        if (optionalHouse.isEmpty()) {
            throw new NotFoundException("House with given id not found");
        }

        return optionalHouse.get();
    }

    /**
     * Add a user to the list of users in a given house.
     *
     * @param house     The house to which the user will be added.
     * @param userToAdd The user to be added to the house.
     */
    private void addUsertoHouse(House house, UserModel userToAdd) {
        house.getUsersList().add(userToAdd);
    }

    /**
     * Get a list of all houses from the repository.
     *
     * @return The list of all houses.
     */
    private List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    /**
     * Convert a list of houses to a JSON string.
     *
     * @param houseList The list of houses to be converted.
     * @return The JSON string representing the list of houses.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    private String convertHouseListToJSONString(List<House> houseList) throws JsonProcessingException {
        return listToJSON.convertListToJSONString(houseList);
    }

    /**
     * Update the address of a given house.
     *
     * @param house      The house to be updated.
     * @param newAddress The new address for the house.
     */
    private void updateHouseAddress(House house, String newAddress) {
        house.setAddress(newAddress);
        houseRepository.save(house);
    }

    /**
     * Get a list of rooms from a given house.
     *
     * @param house The house from which rooms will be retrieved.
     * @return The list of rooms in the house.
     */
    private List<Room> getRoomsFromHouse(House house) {
        return house.getRooms();
    }

    /**
     * Get a list of devices from a list of rooms.
     *
     * @param rooms The list of rooms from which devices will be retrieved.
     * @return The list of devices in the rooms.
     */
    private List<Device> getDevicesFromRooms(List<Room> rooms) {
        List<Device> devices = new ArrayList<>();

        for (Room room : rooms) {
            devices.addAll(room.getDeviceList());
        }

        return devices;
    }

    /**
     * Create a temporary response DTO containing information about a house, its rooms, and devices.
     *
     * @param house   The house for which information is to be included in the response DTO.
     * @param rooms   The list of rooms in the house.
     * @param devices The list of devices in the rooms.
     * @return The temporary response DTO.
     */
    private MiscResponseDTO createTempRespDTO(House house, List<Room> rooms, List<Device> devices) {
        return new MiscResponseDTO(house, rooms, devices);
    }

    /**
     * Convert a temporary response DTO to a JSON string.
     *
     * @param miscResponseDTO The temporary response DTO to be converted.
     * @return The JSON string representing the temporary response DTO.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    private String convertTempRespDTOToJSONString(MiscResponseDTO miscResponseDTO) throws JsonProcessingException {
        return objectToJSON.convertObjToJSONString(miscResponseDTO);
    }
}
