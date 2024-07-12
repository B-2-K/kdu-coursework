/**
 * Utility class for extracting location details from a JSON response.
 */
package com.caching.utils;

import com.caching.customlogger.Logger;
import com.caching.entity.LocationEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;

/**
 * This class provides a method to extract location details from a JSON response.
 */
public class LocationDetails {

    /**
     * Extracts location details from the provided JSON response.
     *
     * @param response The JSON response containing location information.
     * @return A LocationEntity object representing the parsed location details.
     * @throws JSONException If there is an error parsing the JSON response.
     */
    public static LocationEntity locationDetails(JsonNode response) throws JSONException {
        JsonNode results = response.get("data").get(0);
        double latitude = results.get("latitude").asDouble();
        double longitude = results.get("longitude").asDouble();
        String label = results.get("label").asText();
        String postalCode = results.get("postal_code").asText();
        String region = results.get("region").asText();
        int number = results.get("number").asInt();
        Logger.info("Successfully parsed response");
        Logger.info("Latitude: " + latitude + " Longitude: " + longitude);
        return new LocationEntity(label, latitude, longitude, postalCode, region, number);
    }
}
