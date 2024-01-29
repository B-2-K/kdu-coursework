package com.caching.repository;

import com.caching.controller.LocationController;
import com.caching.entity.LocationEntity;
import com.caching.exceptions.InvalidInputProvidedException;
import com.caching.exceptions.LocationNotFoundException;
import com.caching.utils.LocationDetails;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.caching.customlogger.Logger;

/**
 * Repository class for handling location-related operations using an external API.
 */
@Repository
public class LocationRepository {

    @Value("${api-key}")
    private String apiKey;

    private static final String urlGeoCoding = "http://api.positionstack.com/v1/forward?";
    private final RestTemplate callAPI = new RestTemplate();

    /**
     * Retrieves location coordinates for a given address from an external API.
     *
     * @param address The address for which coordinates are to be retrieved.
     * @return LocationEntity representing the location coordinates.
     * @throws LocationNotFoundException If the API response is null or indicates a location not found.
     */
    @Cacheable(cacheNames = "geocoding", key = "#address", unless = "#address.toLowerCase().contains('goa')")
    public LocationEntity getCoordinates(String address) {
        String url = urlGeoCoding + "access_key=" + apiKey + "&query=" + address;
        JsonNode apiResponse = callAPI.getForObject(url, JsonNode.class);
        if (apiResponse == null) {
            throw new LocationNotFoundException("The API response is null. Could not fetch the location");
        }
        return convertResponseToLocation(apiResponse);
    }

    /**
     * Converts the API response to a LocationEntity.
     *
     * @param response The JSON response from the external API.
     * @return LocationEntity representing the parsed location information.
     * @throws InvalidInputProvidedException If the API response indicates invalid input.
     * @throws LocationNotFoundException    If there is an error parsing the location or required data is missing.
     */
    public LocationEntity convertResponseToLocation(JsonNode response) {
        try {
            if (response.get("message") != null) {
                throw new InvalidInputProvidedException("Invalid input provided");
            }
            return LocationDetails.locationDetails(response);
        } catch (Exception e) {
            Logger.error("Error parsing API response: " + e.getMessage());
            throw new LocationNotFoundException("Error while parsing the location");
        }
    }
}
