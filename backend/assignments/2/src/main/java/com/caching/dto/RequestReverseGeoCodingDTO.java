package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for reverse geocoding requests.
 */
@AllArgsConstructor
@Data
public class RequestReverseGeoCodingDTO {

    /**
     * The latitude coordinate for reverse geocoding.
     */
    private String latitude;

    /**
     * The longitude coordinate for reverse geocoding.
     */
    private String longitude;
}
