package com.caching.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity class representing location information.
 */
@AllArgsConstructor
@Data
public class LocationEntity {

    /**
     * A label or name associated with the location.
     */
    private String label;

    /**
     * The latitude coordinate of the location.
     */
    private double latitude;

    /**
     * The longitude coordinate of the location.
     */
    private double longitude;

    /**
     * The postal code of the location.
     */
    private String postalCode;

    /**
     * The region or area associated with the location.
     */
    private String region;

    /**
     * The numerical identifier associated with the location.
     */
    private int number;
}
