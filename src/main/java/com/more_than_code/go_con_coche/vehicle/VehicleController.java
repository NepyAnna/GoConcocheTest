package com.more_than_code.go_con_coche.vehicle;

import com.more_than_code.go_con_coche.vehicle.dtos.VehicleRequest;
import com.more_than_code.go_con_coche.vehicle.dtos.VehicleResponse;
import com.more_than_code.go_con_coche.vehicle.services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @ModelAttribute VehicleRequest vehicleRequest) {
        VehicleResponse createdVehicle = vehicleService.createVehicle(vehicleRequest);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByOwnerId(@PathVariable Long ownerId) {
        List<VehicleResponse> vehicles = vehicleService.getVehicleByOwnerId(ownerId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<VehicleResponse>> getMyVehicles() {
        List<VehicleResponse> vehicles = vehicleService.getMyVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}