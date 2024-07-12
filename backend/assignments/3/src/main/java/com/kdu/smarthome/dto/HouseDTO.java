package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    private String message;
    private String houses;
    private HttpStatus httpStatus;
}
