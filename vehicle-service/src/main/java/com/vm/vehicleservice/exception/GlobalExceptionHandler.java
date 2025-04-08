package com.vm.vehicleservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(VINAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleVINAlreadyExistException(VINAlreadyExistsException exception) {

        System.out.println("Testing");
        log.warn("VIN already exists {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "VIN already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleVehicleNotFoundException(VehicleNotFoundException exception) {

        log.warn("Vehicle not found {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Vehicle not found");
        return ResponseEntity.badRequest().body(errors);
    }
}
