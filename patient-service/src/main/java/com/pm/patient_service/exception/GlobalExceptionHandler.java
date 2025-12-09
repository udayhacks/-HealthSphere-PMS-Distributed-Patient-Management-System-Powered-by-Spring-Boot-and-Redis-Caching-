package com.pm.patient_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)// trigger when jpa validation failed
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exp){

        Map<String, String> errors = new HashMap<>();
        exp.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExitsException(EmailAlreadyExistException ex){
        log.warn("Email already exist",ex.getMessage());
        Map<String , String> errors = new HashMap<>();
        errors.put(" Alert ",ex.getMessage().toString());
        return ResponseEntity.ok().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String , String>> handlePatientNotFoundException (PatientNotFoundException ex){
        log.warn("Patient not Found",ex.getMessage());
        Map<String , String> errors = new HashMap<>();
        errors.put(" Alert ",ex.getMessage().toString());
        return ResponseEntity.ok().body(errors);
    }

}
