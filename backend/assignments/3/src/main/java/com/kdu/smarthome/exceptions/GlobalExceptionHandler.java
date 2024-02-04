package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotAdminException.class)
    public ResponseEntity<ErrorDTO> handleUserNotAdminException(UserNotAdminException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserListEmptyException.class)
    public ResponseEntity<ErrorDTO> handleUserListEmptyException(UserListEmptyException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectUsernamePasswordException.class)
    public ResponseEntity<ErrorDTO> handleIncorrectUsernamePasswordException(IncorrectUsernamePasswordException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorDTO> handleNotAuthorizedException(NotAuthorizedException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }

}
