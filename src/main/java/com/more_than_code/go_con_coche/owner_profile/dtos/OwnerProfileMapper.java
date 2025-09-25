package com.more_than_code.go_con_coche.owner_profile.dtos;

import com.more_than_code.go_con_coche.owner_profile.OwnerProfile;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
public class OwnerProfileMapper {
    public OwnerProfile toEntity (OwnerProfileRequest request){
        return OwnerProfile.builder()
                .build();
    }

    public OwnerProfileResponse toResponse(OwnerProfile ownerProfile){
        return OwnerProfileResponse.builder()
                .id(ownerProfile.getId())
                .registeredUser(ownerProfile.getRegisteredUser().getId())
                .imageUrl(ownerProfile.getImageURL())
                .build();
    }
}
