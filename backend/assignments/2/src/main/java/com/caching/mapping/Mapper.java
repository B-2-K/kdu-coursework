package com.caching.mapping;

import com.caching.dto.ResponseGeoCodingDTO;
import com.caching.dto.ResponseReverseGeoCodingDTO;
import com.caching.entity.LocationEntity;

/**
 * Mapper class for converting between different types of location-related objects.
 */
public class Mapper {

    private Mapper() {
    }

    /**
     * Converts a LocationEntity to a ResponseForwardGeocoding.
     *
     * @param location The LocationEntity to be converted.
     * @return ResponseForwardGeocoding representing forward geocoding response.
     */
    public static ResponseGeoCodingDTO convertToForwardResponse(LocationEntity location) {
        return new ResponseGeoCodingDTO(location.getLatitude(), location.getLongitude());
    }

    /**
     * Converts a LocationEntity to a ResponseReverseGeocoding.
     *
     * @param location The LocationEntity to be converted.
     * @return ResponseReverseGeocoding representing reverse geocoding response.
     */
    public static ResponseReverseGeoCodingDTO convertToReverseResponse(LocationEntity location) {
        return new ResponseReverseGeoCodingDTO(location.getLabel());
    }

    /**
     * Creates an instance of the Mapper class.
     *
     * @return A new instance of the Mapper class.
     */
    public static Mapper createMapper() {
        return new Mapper();
    }
}
