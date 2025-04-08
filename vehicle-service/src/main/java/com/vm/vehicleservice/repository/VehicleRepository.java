package com.vm.vehicleservice.repository;
import com.vm.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    boolean existsByVIN(String VIN);
    boolean existsByVINAndIdNot(String VIN, UUID id);
}
