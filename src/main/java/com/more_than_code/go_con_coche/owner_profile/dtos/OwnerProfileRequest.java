package com.more_than_code.go_con_coche.owner_profile.dtos;


import org.springframework.web.multipart.MultipartFile;

public record OwnerProfileRequest (
        MultipartFile image
){
}
