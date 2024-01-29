package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for reverse geocoding responses.
 */
@AllArgsConstructor
@Data
public class ResponseReverseGeoCodingDTO {

    /**
     * The address associated with the reverse geocoding response.
     */
    private String address;
}
