package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import lombok.Data;

@Data
public class UserListEmptyException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public UserListEmptyException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,404);
    }
}
