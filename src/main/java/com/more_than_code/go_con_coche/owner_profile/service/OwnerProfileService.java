package com.more_than_code.go_con_coche.owner_profile.service;

import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileRequest;
import com.more_than_code.go_con_coche.owner_profile.dtos.OwnerProfileResponse;

public interface OwnerProfileService {
    OwnerProfileResponse createOwnerProfile(OwnerProfileRequest ownerProfileRequest);
}
