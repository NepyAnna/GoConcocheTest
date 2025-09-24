package com.more_than_code.go_con_coche.vehicle.dtos;

import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileResponse;
import com.more_than_code.go_con_coche.vehicle.models.FuelTypeCar;
import com.more_than_code.go_con_coche.vehicle.models.Seater;
import lombok.Builder;

@Builder
public record VehicleResponse (
        Long id,
        String vin,
        String plateNumber,
        String insuranceNumber,
        String model,
        String brand,
        Integer year,
        String color,
        Seater seater,
        Integer childSeatsNumber,
        FuelTypeCar fuelTypeCar,
        String fuelConsumption,
        String imageUrl,
        String username
){
}