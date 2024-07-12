package com.kdu.smarthome.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseRequestDTO {
    private String address;

    @JsonProperty("house_name")
    private String houseName;
}
