package com.backendmonolitico.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.backendmonolitico.Model.erro.ErrorMessage;
import com.backendmonolitico.Model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<
            ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex){

        ErrorMessage erro = new ErrorMessage("NOT FOUND",HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    
}
