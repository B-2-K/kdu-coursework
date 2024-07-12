package com.kdu.smarthome.Utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Utility class for converting a list of objects to a JSON string.
 */
@Component
public class ListToJSON {

    /**
     * Converts a list of objects to a JSON string.
     *
     * @param objList The list of objects to be converted.
     * @param <T>     The type of objects in the list.
     * @return The JSON representation of the list.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    public <T> String convertListToJSONString(List<T> objList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(objList);
    }
}
