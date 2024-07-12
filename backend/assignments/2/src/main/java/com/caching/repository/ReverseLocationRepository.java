package com.caching.repository;

import com.caching.customlogger.Logger;
import com.caching.entity.LocationEntity;
import com.caching.exceptions.InvalidInputProvidedException;
import com.caching.exceptions.LocationNotFoundException;
import com.caching.utils.LocationDetails;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Repository class for handling reverse geocoding operations using an external API.
 */
@Repository
public class ReverseLocationRepository {

    @Value("${api-key}")
    private String apiKey;

    private static final String urlReverseGeoCoding = "http://api.positionstack.com/v1/reverse";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves the location label for a given latitude and longitude using reverse geocoding from an external API.
     *
     * @param latitude  The latitude for reverse geocoding.
     * @param longitude The longitude for reverse geocoding.
     * @return LocationEntity representing the reverse geocoding response.
     * @throws LocationNotFoundException    If the API response is null or indicates a location not found.
     * @throws InvalidInputProvidedException If there is an error parsing the location or required data is missing.
     */
    @Cacheable(cacheNames = "reverse-geocoding", key = "{#latitude,#longitude}")
    public LocationEntity getAddressLabel(String latitude, String longitude) {
        if (checkCoordinates(latitude, longitude)) {
            String url = urlReverseGeoCoding + "?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            if (response == null) {
                throw new LocationNotFoundException("Error while fetching location in reverse geocoding");
            }
            return convertResponseToLocation(response);
        } else {
            throw new InvalidInputProvidedException("Invalid Coordinates provided");
        }
    }

    /**
     * Converts the API response to a LocationEntity for reverse geocoding.
     *
     * @param response The JSON response from the external API.
     * @return LocationEntity representing the parsed reverse geocoding information.
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
            throw new LocationNotFoundException("Error while fetching and parsing Reverse Geocoding");
        }
    }

    /**
     * Checks if the provided latitude and longitude values are valid coordinates.
     *
     * @param lat  The latitude value.
     * @param longi The longitude value.
     * @return true if coordinates are valid, otherwise false.
     * @throws InvalidInputProvidedException If the coordinate values are invalid or not in a proper format.
     */
    public boolean checkCoordinates(String lat, String longi) {
        try {
            Double latitude = Double.parseDouble(lat);
            Double longitude = Double.parseDouble(longi);
            if ((latitude <= 90.0 && latitude >= -90.0) && (longitude <= 180.0 && longitude >= -180.0)) {
                return true;
            } else {
                throw new InvalidInputProvidedException("The coordinate values are invalid");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputProvidedException("Invalid format for coordinates");
        }
    }
}
