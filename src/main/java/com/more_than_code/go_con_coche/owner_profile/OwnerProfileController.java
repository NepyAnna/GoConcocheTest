package com.more_than_code.go_con_coche.owner_profile;

import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileRequest;
import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileResponse;
import com.more_than_code.go_con_coche.owner_profile.service.OwnerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/owner-profiles")
@RequiredArgsConstructor
public class OwnerProfileController {
    private final OwnerProfileService ownerProfileService;

    @PostMapping("")
    public ResponseEntity<OwnerProfileResponse> createOwnerProfile(@Valid @ModelAttribute OwnerProfileRequest ownerProfileRequest){
        OwnerProfileResponse createdOwnerProfile = ownerProfileService.createOwnerProfile(ownerProfileRequest);
        return new ResponseEntity<>(createdOwnerProfile, HttpStatus.CREATED);
    }
}
