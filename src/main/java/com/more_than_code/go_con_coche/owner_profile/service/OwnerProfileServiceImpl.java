package com.more_than_code.go_con_coche.owner_profile.service;

import com.more_than_code.go_con_coche.cloudinary.CloudinaryService;
import com.more_than_code.go_con_coche.cloudinary.DefaultImageType;
import com.more_than_code.go_con_coche.cloudinary.UploadResult;
import com.more_than_code.go_con_coche.global.EntityAlreadyExistsException;
import com.more_than_code.go_con_coche.owner_profile.OwnerProfile;
import com.more_than_code.go_con_coche.owner_profile.OwnerProfileRepository;
import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileMapper;
import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileRequest;
import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileResponse;
import com.more_than_code.go_con_coche.registered_user.RegisteredUser;
import com.more_than_code.go_con_coche.registered_user.services.UserAuthService;
import com.more_than_code.go_con_coche.global.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerProfileServiceImpl implements OwnerProfileService {
    private final OwnerProfileRepository ownerProfileRepository;
    private final UserAuthService userAuthService;
    private final OwnerProfileMapper ownerProfileMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public OwnerProfileResponse createOwnerProfile(OwnerProfileRequest ownerProfileRequest) {
        OwnerProfile ownerProfile = ownerProfileMapper.toEntity(ownerProfileRequest);
        RegisteredUser user = userAuthService.getAuthenticatedUser();
        ownerProfile.setRegisteredUser(user);

        if (ownerProfileRepository.findByRegisteredUserId(user.getId()).isPresent()) {
            throw new EntityAlreadyExistsException(OwnerProfile.class.getSimpleName(), "user", user.getUsername());
        }

        UploadResult uploadResult = cloudinaryService.resolveImage(
                ownerProfileRequest.image(), DefaultImageType.PROFILE
        );
        ownerProfile.setImageURL(uploadResult.url());
        ownerProfile.setImageURL(uploadResult.publicId());

        OwnerProfile savedOwnerProfile = ownerProfileRepository.save(ownerProfile);

        return ownerProfileMapper.toResponse(savedOwnerProfile);
    }

    public OwnerProfile getOwnerProfileById(Long id) {
        return ownerProfileRepository.findByRegisteredUserId(id)
                .orElseThrow(() -> new EntityNotFoundException(OwnerProfile.class.getSimpleName(),"id", id.toString()));
    }
}

