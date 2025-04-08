package com.vm.vehicleservice;

import com.vm.vehicleservice.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.pm.vehicleservice.service",
		"com.pm.vehicleservice.controller", "com.pm.vehicleservice.grpc",
		"com.pm.vehicleservice.kafka"})
@EnableJpaRepositories("com.vm.vehicleservice.repository")
@Import(GlobalExceptionHandler.class)
public class VehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}

}
