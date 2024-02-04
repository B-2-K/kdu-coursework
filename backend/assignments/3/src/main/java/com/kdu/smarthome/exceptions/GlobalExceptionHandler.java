package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO;
import com.kdu.smarthome.exceptions.customexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a class that handles exceptions globally for the Smart Home application.
 * It provides specific exception handling methods with appropriate responses for different custom exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions.
     *
     * @param ex The general exception to be handled.
     * @return ResponseEntity with an error message and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles UserNotAdminException.
     *
     * @param ex The UserNotAdminException to be handled.
     * @return ResponseEntity with the error details and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(UserNotAdminException.class)
    public ResponseEntity<ErrorDTO> handleUserNotAdminException(UserNotAdminException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles UserListEmptyException.
     *
     * @param ex The UserListEmptyException to be handled.
     * @return ResponseEntity with the error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(UserListEmptyException.class)
    public ResponseEntity<ErrorDTO> handleUserListEmptyException(UserListEmptyException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NotFoundException.
     *
     * @param ex The NotFoundException to be handled.
     * @return ResponseEntity with the error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IncorrectUsernamePasswordException.
     *
     * @param ex The IncorrectUsernamePasswordException to be handled.
     * @return ResponseEntity with the error details and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(IncorrectUsernamePasswordException.class)
    public ResponseEntity<ErrorDTO> handleIncorrectUsernamePasswordException(IncorrectUsernamePasswordException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles NotAuthorizedException.
     *
     * @param ex The NotAuthorizedException to be handled.
     * @return ResponseEntity with the error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorDTO> handleNotAuthorizedException(NotAuthorizedException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidCredentialsException.
     *
     * @param ex The InvalidCredentialsException to be handled.
     * @return ResponseEntity with the error details and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }
}
