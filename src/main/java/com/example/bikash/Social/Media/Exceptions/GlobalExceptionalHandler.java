package com.example.bikash.Social.Media.Exceptions;

import com.example.bikash.Social.Media.Responses.APiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APiResponse> resourceNotFoundException( ResourceNotFoundException ex )
    {

        String message = ex.getMessage();
        APiResponse response = new APiResponse(message,false);
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicationItemException.class)
    public ResponseEntity<APiResponse> DuplicationItemException( DuplicationItemException ex )
    {

        String message = ex.getMessage();
        APiResponse response = new APiResponse(message,false);
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




}


