package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import lombok.Data;

@Data
public class UserNotAdminException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public UserNotAdminException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,401);
    }
}
