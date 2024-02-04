package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import lombok.Data;

@Data
public class IncorrectUsernamePasswordException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public IncorrectUsernamePasswordException(String message){
        super(message);
        this.errorDTO = new ErrorDTO(message,401);
    }
}
