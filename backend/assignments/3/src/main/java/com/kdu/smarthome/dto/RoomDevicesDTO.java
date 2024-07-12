package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDevicesDTO {
    private String message;
    private String roomsAndDevices;
    private HttpStatus httpStatus;
}
