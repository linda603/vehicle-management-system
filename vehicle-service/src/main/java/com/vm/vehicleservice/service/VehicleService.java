package com.vm.vehicleservice.service;

import com.vm.vehicleservice.dto.VehicleRequestDTO;
import com.vm.vehicleservice.dto.VehicleResponseDTO;
import com.vm.vehicleservice.exception.VINAlreadyExistsException;
import com.vm.vehicleservice.exception.VehicleNotFoundException;
import com.vm.vehicleservice.mapper.VehicleMapper;
import com.vm.vehicleservice.model.Vehicle;
import com.vm.vehicleservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleResponseDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(VehicleMapper::toDTO).toList();
    }

    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleRequestDTO) {
        if(vehicleRepository.existsByVIN(vehicleRequestDTO.getVIN())) {
            throw new VINAlreadyExistsException("VIN number " + vehicleRequestDTO.getVIN() +  " is already registered");
        }

        Vehicle newPatient = vehicleRepository.save(
                VehicleMapper.toModel(vehicleRequestDTO));

        return VehicleMapper.toDTO(newPatient);
    }

    public VehicleResponseDTO updateVehicle(UUID id, VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new VehicleNotFoundException("Vehicle not found, id: " + id));

        if(vehicleRepository.existsByVINAndIdNot(vehicleRequestDTO.getVIN(), id)) {
            throw new VINAlreadyExistsException("VIN number " + vehicleRequestDTO.getVIN() +  " is already registered");
        }
        vehicle.setVIN(vehicleRequestDTO.getVIN());

        Vehicle updatedVehicle = vehicleRepository.save(
                vehicle);
        return VehicleMapper.toDTO(updatedVehicle);
    }

    public void deleteVehicle(UUID id) {
        vehicleRepository.deleteById(id);
    }
}
