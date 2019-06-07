package com.stackroute.muzixservice.exceptions;

import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value= TrackAlreadyExistsException.class)
    public ResponseEntity<String> returnTrackAlreadyExistsException(TrackAlreadyExistsException trackAlreadyExistsException)
    {

        return new ResponseEntity<String>(trackAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value= TrackNotFoundException.class)
    public ResponseEntity<String> returnTrackNotFoundException(TrackNotFoundException trackNotFoundException)
    {
        return new ResponseEntity<String>(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}

