package com.excellentbookshop.catalogservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //mark a class as a centralized exception handler
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class) //Define exception for which handler must be executed
    @ResponseStatus(HttpStatus.NOT_FOUND)
        //Defines the status code for the HTTP response created when the exception is thrown
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();  //The message that will be included in the HTTP response body
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String bookAlreadyExistsHandler(BookAlreadyExistException ex) {
        return ex.getMessage();
    }


    //Handles the exception thrown when the Book validation fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            //Collects meaningful error messages about which Book fields were invalid instead of returning an empty message
        });
        return errors;
    }
}
