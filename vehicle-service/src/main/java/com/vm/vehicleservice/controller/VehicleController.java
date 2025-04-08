package com.vm.vehicleservice.controller;

import com.vm.vehicleservice.dto.VehicleRequestDTO;
import com.vm.vehicleservice.dto.VehicleResponseDTO;
import com.vm.vehicleservice.dto.validators.CreateVehicleValidationGroup;
import com.vm.vehicleservice.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles") // http://localhost:4000/vehicles
@Tag(name = "Vehicle", description = "API for managing vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Get vehicles")
    public ResponseEntity<List<VehicleResponseDTO> > getVehicles() {
        List<VehicleResponseDTO> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok().body(vehicles);
    }

    @PostMapping
    @Operation(summary = "Create a new Vehicle")
    public ResponseEntity<VehicleResponseDTO> createVehicle(
            @Validated({Default.class, CreateVehicleValidationGroup.class})
            @RequestBody VehicleRequestDTO vechileRequestDTO){
            VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(vechileRequestDTO);
        return ResponseEntity.ok().body(vehicleResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Vehicle")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable UUID id, @Validated({Default.class}) @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.updateVehicle(id, vehicleRequestDTO);
        return ResponseEntity.ok().body(vehicleResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Vehicle")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
