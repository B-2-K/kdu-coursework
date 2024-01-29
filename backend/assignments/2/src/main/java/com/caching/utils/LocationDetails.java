package com.caching.utils;

import com.caching.customlogger.Logger;
import com.caching.entity.LocationEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;

public class LocationDetails {
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
