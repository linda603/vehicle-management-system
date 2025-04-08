package com.vm.vehicleservice.exception;

public class VINAlreadyExistsException extends RuntimeException {
  public VINAlreadyExistsException(String message) {
    super(message);
  }
}
