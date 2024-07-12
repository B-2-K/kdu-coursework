package com.caching.service;

import com.caching.dto.RequestReverseGeoCodingDTO;
import com.caching.dto.ResponseGeoCodingDTO;
import com.caching.dto.ResponseReverseGeoCodingDTO;
import com.caching.entity.LocationEntity;
import com.caching.mapping.Mapper;
import com.caching.repository.LocationRepository;
import com.caching.repository.ReverseLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling location-related operations.
 */
@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final ReverseLocationRepository reverseLocationRepository;

    /**
     * Constructor for LocationService.
     *
     * @param locationRepository           The repository for location-related operations.
     * @param reverseLocationRepository    The repository for reverse location-related operations.
     */
    @Autowired
    public LocationService(LocationRepository locationRepository, ReverseLocationRepository reverseLocationRepository) {
        this.reverseLocationRepository = reverseLocationRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * Retrieves latitude and longitude coordinates for a given address.
     *
     * @param address The address for which coordinates are to be retrieved.
     * @return ResponseForwardGeocoding representing the forward geocoding response.
     */
    public ResponseGeoCodingDTO getLatitudeLongitude(String address) {
        LocationEntity current = locationRepository.getCoordinates(address);
        return Mapper.convertToForwardResponse(current);
    }

    /**
     * Retrieves address details for a given latitude and longitude.
     *
     * @param requestReverseGeoCodingDto The DTO containing latitude and longitude for reverse geocoding.
     * @return ResponseReverseGeocoding representing the reverse geocoding response.
     */
    public ResponseReverseGeoCodingDTO getAddressDetails(RequestReverseGeoCodingDTO requestReverseGeoCodingDto) {
        LocationEntity current = reverseLocationRepository.getAddressLabel(requestReverseGeoCodingDto.getLatitude(), requestReverseGeoCodingDto.getLongitude());
        return Mapper.convertToReverseResponse(current);
    }
}
