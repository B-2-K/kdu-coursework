package com.kdu.smarthome.Utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JSONUtil {

    /**
     * Converts List to JSON string
     * @param objList
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public <T> String convertListToJSONString(List<T> objList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(objList);
    }

    /**
     * Converts object to JSON string
     * @param object
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public <T> String convertObjToJSONString(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}
