package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for forward geocoding responses.
 */
@AllArgsConstructor
@Data
public class ResponseGeoCodingDTO {

    /**
     * The latitude coordinate associated with the forward geocoding response.
     */
    private double latitude;

    /**
     * The longitude coordinate associated with the forward geocoding response.
     */
    private double longitude;
}
