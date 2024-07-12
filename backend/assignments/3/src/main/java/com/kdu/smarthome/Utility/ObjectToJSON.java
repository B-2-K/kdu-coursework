package com.kdu.smarthome.Utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

/**
 * Utility class for converting an object to a JSON string.
 */
@Component
public class ObjectToJSON {

    /**
     * Converts an object to a JSON string.
     *
     * @param object The object to be converted.
     * @param <T>    The type of the object.
     * @return The JSON representation of the object.
     * @throws JsonProcessingException If there is an issue processing JSON.
     */
    public <T> String convertObjToJSONString(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}
