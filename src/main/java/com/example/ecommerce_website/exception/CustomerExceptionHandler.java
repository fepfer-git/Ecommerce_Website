package com.example.ecommerce_website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex){
          ErrorResponse errorResponse = new ErrorResponse(
                  ex.getMessage(),
                  HttpStatus.NOT_FOUND,
                  ZonedDateTime.now()
          );
          return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

}
