package com.vm.vehicleservice.dto;

import com.vm.vehicleservice.dto.validators.CreateVehicleValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VehicleRequestDTO {
    @NotBlank(message = "VIN is required")
    @Size(max = 100, message = "VIN cannot exceed 100 characters and should be valid")
    private String VIN;

    @NotBlank(groups = CreateVehicleValidationGroup.class, message = "Registered date is required")
    private String registeredDate;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}

