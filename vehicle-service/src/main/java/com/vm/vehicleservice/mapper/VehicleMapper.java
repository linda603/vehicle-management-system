package com.vm.vehicleservice.mapper;

import com.vm.vehicleservice.dto.VehicleRequestDTO;
import com.vm.vehicleservice.dto.VehicleResponseDTO;
import com.vm.vehicleservice.model.Vehicle;

import java.time.LocalDate;

public class VehicleMapper {
    public static VehicleResponseDTO toDTO(Vehicle vehicle) {
        VehicleResponseDTO vehicleDTO = new VehicleResponseDTO();
        vehicleDTO.setId(vehicle.getId().toString());
        vehicleDTO.setVIN(vehicle.getVIN());
        return vehicleDTO;
    }

    public static Vehicle toModel(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVIN(vehicleRequestDTO.getVIN());
        vehicle.setRegisteredDate(LocalDate.parse(vehicleRequestDTO.getRegisteredDate()));
        return vehicle;
    }
}
