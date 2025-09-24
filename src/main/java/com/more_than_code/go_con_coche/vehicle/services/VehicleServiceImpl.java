package com.more_than_code.go_con_coche.vehicle.services;

import com.more_than_code.go_con_coche.cloudinary.CloudinaryService;
import com.more_than_code.go_con_coche.cloudinary.DefaultImageType;
import com.more_than_code.go_con_coche.cloudinary.UploadResult;
import com.more_than_code.go_con_coche.global.EntityAlreadyExistsException;
import com.more_than_code.go_con_coche.global.EntityNotFoundException;
import com.more_than_code.go_con_coche.owner_profile.OwnerProfile;
import com.more_than_code.go_con_coche.owner_profile.OwnerProfileRepository;
import com.more_than_code.go_con_coche.registered_user.RegisteredUser;
import com.more_than_code.go_con_coche.registered_user.services.UserAuthService;
import com.more_than_code.go_con_coche.vehicle.VehicleRepository;
import com.more_than_code.go_con_coche.vehicle.dtos.VehicleMapper;
import com.more_than_code.go_con_coche.vehicle.dtos.VehicleRequest;
import com.more_than_code.go_con_coche.vehicle.dtos.VehicleResponse;
import com.more_than_code.go_con_coche.vehicle.models.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final OwnerProfileRepository ownerProfileRepository;
    private final UserAuthService userAuthService;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public VehicleResponse createVehicle(VehicleRequest vehicleRequest) {
        RegisteredUser user = userAuthService.getAuthenticatedUser();
        if (vehicleRepository.existsByVin(vehicleRequest.vin())) {
            throw new EntityAlreadyExistsException("Vehicle", "VIN", vehicleRequest.vin());
        }
        if (vehicleRepository.existsByPlateNumber(vehicleRequest.plateNumber())) {
            throw new EntityAlreadyExistsException("Vehicle", "plate number", vehicleRequest.plateNumber());
        }
        if (vehicleRepository.existsByInsuranceNumber(vehicleRequest.insuranceNumber())) {
            throw new EntityAlreadyExistsException("Vehicle", "insurance number", vehicleRequest.insuranceNumber());
        }

        OwnerProfile owner = ownerProfileRepository.findByRegisteredUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("OwnerProfile", "id", user.getId().toString()));

        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        vehicle.setOwner(owner);
        UploadResult uploadResult = resolveVehicleImage(vehicleRequest.image());
        vehicle.setImageUrl(uploadResult.url());
        vehicle.setPublicImageId(uploadResult.publicId());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> getVehicleByOwnerId(Long ownerId) {
        OwnerProfile owner = ownerProfileRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("OwnerProfile", "id", ownerId.toString()));

        List<Vehicle> vehicles = vehicleRepository.findByOwner(owner);
        return vehicles.stream()
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
    }

    private UploadResult resolveVehicleImage(MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                return  cloudinaryService.upload(image, "vehicles");
            } else {
                return cloudinaryService.uploadDefault(DefaultImageType.CAR);
            }
        } catch (Exception e) {
            return cloudinaryService.uploadDefault(DefaultImageType.CAR);
        }
    }
}