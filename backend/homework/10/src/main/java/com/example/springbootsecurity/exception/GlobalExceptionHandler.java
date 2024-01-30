package com.example.springbootsecurity.exception;

import com.example.springbootsecurity.exception.custom.MyCustomException;
import com.example.springbootsecurity.exception.custom.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<String> handleCustomException(MyCustomException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> handleCustomException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> genericException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
