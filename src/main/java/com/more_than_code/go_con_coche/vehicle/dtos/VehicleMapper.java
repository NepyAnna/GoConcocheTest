package com.more_than_code.go_con_coche.vehicle.dtos;

import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileResponse;
import com.more_than_code.go_con_coche.vehicle.models.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequest vehicleRequest) {
        return Vehicle.builder()
                .vin(vehicleRequest.vin())
                .plateNumber(vehicleRequest.plateNumber())
                .insuranceNumber(vehicleRequest.insuranceNumber())
                .model(vehicleRequest.model())
                .brand(vehicleRequest.brand())
                .year(vehicleRequest.year())
                .color(vehicleRequest.color())
                .seater(vehicleRequest.seater())
                .childSeatsNumber(vehicleRequest.childSeatsNumber())
                .fuelTypeCar(vehicleRequest.fuelTypeCar())
                .fuelConsumption(vehicleRequest.fuelConsumption())
                .build();
    }

    public VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vin(vehicle.getVin())
                .plateNumber(vehicle.getPlateNumber())
                .insuranceNumber(vehicle.getInsuranceNumber())
                .model(vehicle.getModel())
                .brand(vehicle.getBrand())
                .year(vehicle.getYear())
                .color(vehicle.getColor())
                .seater(vehicle.getSeater())
                .childSeatsNumber(vehicle.getChildSeatsNumber())
                .fuelTypeCar(vehicle.getFuelTypeCar())
                .fuelConsumption(vehicle.getFuelConsumption())
                .imageUrl(vehicle.getImageUrl())
                .username(vehicle.getOwner().getRegisteredUser().getUsername())
                .build();
    }
}